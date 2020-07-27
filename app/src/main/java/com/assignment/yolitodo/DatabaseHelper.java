package com.assignment.yolitodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ToDoList.db";
    public static final String TABLE_NAME = "parents";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, parentname TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String SELECT_DELETE_ENTRIES = "select * from " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public boolean addText (String text) {
        // Get writable database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Create content Values
        ContentValues contentValues = new ContentValues();
        contentValues.put("parentname", text);

        // Add values to database
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return true;
    }

    public ArrayList getAllItems(){
        // Get readable database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        // Create arrayList to hold values
        ArrayList<String> items = new ArrayList<>();

        //Create cursor to select values
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_DELETE_ENTRIES,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            items.add(cursor.getString(cursor.getColumnIndex("parentname")));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }


}
