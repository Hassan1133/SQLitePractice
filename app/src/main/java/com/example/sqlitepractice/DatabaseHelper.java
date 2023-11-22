package com.example.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydb";
    public static final String TABLE_NAME = "student";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ID = "id";
    public static final int DATABASE_VERSION = 1;
    private static final String TAG = "DatabaseHelper";
    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID + " integer primary key autoincrement, " + NAME + " text, " + PHONE + " integer unique)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    long insertData(String name, String phone) {
        Log.d(TAG, "insertData: " + CREATE_TABLE);
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(PHONE, phone);
        long response = database.insert(TABLE_NAME, null, contentValues);
        return response;
    }

    Cursor fetchData() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    int deleteData() {
        SQLiteDatabase database = this.getWritableDatabase();
        int res = database.delete(TABLE_NAME, null, null);
        return res;
    }

    int deleteSpecData(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        int res = database.delete(TABLE_NAME, ID + "=" + id, null);
        return res;
    }

    int update(String name, int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        int res = database.update(TABLE_NAME, contentValues, ID + "=" + id, null);
        return res;
    }
}
