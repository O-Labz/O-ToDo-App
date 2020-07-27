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
    public static final String REMINDER_TABLE_NAME = "children";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, parentname TEXT)";

    private static final String SQL_CREATE_REMINDER_ENTRIES = "CREATE TABLE " + REMINDER_TABLE_NAME + "(id INTEGER PRIMARY KEY, childname TEXT, childparentid INTEGER)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String SQL_DELETE_REMINDER_ENTRIES = "DROP TABLE IF EXISTS " + REMINDER_TABLE_NAME;

    private static final String SELECT_PARENT_ENTRIES = "select * from " + TABLE_NAME;

    private static final String SELECT_CHILDREN_ENTRIES = "select * from " + REMINDER_TABLE_NAME + " where childparentid = ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_CREATE_REMINDER_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_REMINDER_ENTRIES);
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

    public boolean addReminder (String text, int position) {
        // Get writable database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Create content Values
        ContentValues contentValues = new ContentValues();
        contentValues.put("childname", text);
        contentValues.put("childparentid", position);

        // Add values to database
        sqLiteDatabase.insert(REMINDER_TABLE_NAME,null,contentValues);

        sqLiteDatabase.close();

        return true;
    }

    public ArrayList getAllItems(){
        // Get readable database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        // Create arrayList to hold values
        ArrayList<String> items = new ArrayList<>();

        //Create cursor to select values
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_PARENT_ENTRIES,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            items.add(cursor.getString(cursor.getColumnIndex("parentname")));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    public ArrayList getAllReminderItems(int position){
        // Get readable database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        // Create arrayList to hold values
        ArrayList<String> items = new ArrayList<>();

        //Create cursor to select values
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_CHILDREN_ENTRIES + position,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            items.add(cursor.getString(cursor.getColumnIndex("childname")));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }


}
