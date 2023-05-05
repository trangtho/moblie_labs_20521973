package com.example.lab03_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lab03_sqlite.DatabaseHelper;

import java.io.UnsupportedEncodingException;

public class DbAdapter {
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public  static final String KEY_PHONE = "phone";
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final String DATABASE_NAME = "Database_Demo";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 2;
    private final Context context;

    public DbAdapter(Context ctx) {
        this.context = ctx;
    }

    public DbAdapter open() {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long createUser(String name, String phone) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, name);
        inititalValues.put(KEY_PHONE, phone);
        //inititalValues.put(KEY_NAME, name, convertToUTF8(name));
        //inititalValues.put(KEY_PHONE, phone,convertToUTF8(phone));
        return sqLiteDatabase.insert(DATABASE_TABLE, null, inititalValues);
    }

    private byte[] convertToUTF8(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public boolean deleteUser(long rowId) {
        return sqLiteDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
    }

    public boolean deleteAllUsers() {
        return sqLiteDatabase.delete(DATABASE_TABLE, null, null) > 0;
    }

    public Cursor getAllUsers() {
        Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_NAME, KEY_PHONE}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

}
