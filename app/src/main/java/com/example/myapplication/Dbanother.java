package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbanother extends SQLiteOpenHelper {
    public static final String dbname ="my_lib.db";
    public Dbanother(Context context) {
        super(context, dbname, null, 1);
    }
    public static final String qry1 = "CREATE TABLE users( username text,bname text, primary key(username,bname), foreign key (bname) references lib_book(bname))";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }
    public Boolean inserts(String uname, String bname ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bname",bname);
        cv.put("username",uname);
        long res = db.insert("users",null,cv);
        if(res==-1)
        {
            return false;
        }
        else
            return true;
    }
}
