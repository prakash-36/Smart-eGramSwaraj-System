package com.example.myproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "MyProject.db";

    public DBHelper(Context context) {
        super(context, DBNAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (username varchar(15) primary key, email varchar(20), password varchar(20));");
        db.execSQL("create table local_body (state varchar(20), district varchar(20), taluk varchar(20), village varchar(20));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists local_body");
        onCreate(db);
    }

    public boolean insertData(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert("users",null,contentValues);
        return result != -1;
    }

    public boolean insertLocalBody(String state, String district, String taluk, String village){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("state",state);
        contentValues.put("district",district);
        contentValues.put("taluk",taluk);
        contentValues.put("village",village);
        long result = db.insert("local_body",null,contentValues);
        return result != -1;
    }

    public boolean checkUserName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?",new String[] {username});
        return cursor.getCount() > 0;
    }

    public boolean checkUserPassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ?",new String[]{username,password});
        return cursor.getCount() > 0;
    }

    public ArrayList<String> getItems(String name, String prev_name, String value) {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select distinct "+name+" from local_body where "+prev_name+" = ?",new String[]{value});
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(name)));
            res.moveToNext();
        }
        return array_list;
    }
}
