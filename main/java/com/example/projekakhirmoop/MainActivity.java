package com.example.projekakhirmoop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declaration
    private EditText mEditTextItem;
    private TextView mTextViewItem;
    private int mAmount = 0;
    private SQLiteDatabase mDatabase;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new Adapter(this,getAllItems());
        recyclerView.setAdapter(mAdapter);

        //for deleting items
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        mEditTextItem = findViewById(R.id.et_product);
        mTextViewItem = findViewById(R.id.tv_amount);
        Button buttonIncrease = findViewById(R.id.tambah);
        Button buttonDecrease = findViewById(R.id.kurang);
        Button buttonAdd = findViewById(R.id.addnew);

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void increase(){
        mAmount++;
        mTextViewItem.setText(String.valueOf(mAmount));
    }
    private void decrease(){
        if(mAmount>0){
            mAmount--;
            mTextViewItem.setText(String.valueOf(mAmount));
        }
    }
    private void addItem(){
        if(mEditTextItem.getText().toString().trim().length() == 0 || mAmount == 0){
            Toast.makeText(this,"Minimum Quantity is 0 and No Space Please", Toast.LENGTH_LONG).show();
            return;
        }

        String name = mEditTextItem.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(GroceryItems.GroceryEntry.COLUMN_NAME, name);
        cv.put(GroceryItems.GroceryEntry.COLUMN_AMOUNT, mAmount);

        //masukin dari cv ke tables
        mDatabase.insert(GroceryItems.GroceryEntry.TABLE_NAME, null,cv);

        //update cursor dan display ke rv
        mAdapter.swapCursor(getAllItems() );

        //hapus lagi untuk bisa input lagi
        mEditTextItem.getText().clear();


    }

    private void removeItem(long id){
        mDatabase.delete(GroceryItems.GroceryEntry.TABLE_NAME, GroceryItems.GroceryEntry._ID + "=" + id,null);

        mAdapter.swapCursor(getAllItems());
    }

    //ambil semua data di db dan tampilin sesuai timestamp
    private Cursor getAllItems(){
        return mDatabase.query(GroceryItems.GroceryEntry.TABLE_NAME,
                null,null,null,null,null, GroceryItems.GroceryEntry.COLUMN_TIMESTAMP +
                " DESC");
    }

}