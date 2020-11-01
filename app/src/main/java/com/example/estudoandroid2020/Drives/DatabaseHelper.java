package com.example.estudoandroid2020.Drives;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Login.db",null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    //insert
    public boolean insert(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user",null,contentValues);
        return ins != -1;
    }

    //check email
    public Boolean checkmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from user where email=?", new String[]{email});
        return c.getCount() <= 0;
    }

    //check email and password
    public  Boolean checkemailPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from user where email = ? and password=?", new String[]{email,password});
        return c.getCount() > 0;
    }
}
