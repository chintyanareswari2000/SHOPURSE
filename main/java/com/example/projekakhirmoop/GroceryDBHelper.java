package com.example.projekakhirmoop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GroceryDBHelper extends SQLiteOpenHelper {

    //declare nama table untuk database
    public static final String DATABASE_NAME = "grocerylist.db";
    public static final int DATABASE_VERSION = 1; //klo mau nambah kolom harus di add by 1


    public GroceryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " + GroceryItems.GroceryEntry.TABLE_NAME + "(" + GroceryItems.GroceryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GroceryItems.GroceryEntry.COLUMN_NAME +
                " TEXT NOT NULL, " + GroceryItems.GroceryEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " + GroceryItems.GroceryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";

        //execute SQL nya
        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }

    //kalau db version di increment
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GroceryItems.GroceryEntry.TABLE_NAME);
    }
}
