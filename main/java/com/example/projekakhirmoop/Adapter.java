package com.example.projekakhirmoop;

import android.content.Context;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.GroceryViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public Adapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder{
        public TextView nameText;
        public  TextView countText;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.tv_item_name);
            countText = itemView.findViewById(R.id.tv_amount_item);
        }
    }

    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grocery_item, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        //memastikan curson bisa berpindah
        if (!mCursor.moveToPosition( position)){
            return;
        }


        String name = mCursor.getString(mCursor.getColumnIndex(GroceryItems.GroceryEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(GroceryItems.GroceryEntry.COLUMN_AMOUNT));
        long id = mCursor.getLong(mCursor.getColumnIndex(GroceryItems.GroceryEntry._ID));


        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));

        holder.itemView.setTag(id);
    }

    //return semua isi db
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;

        //update rv
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
