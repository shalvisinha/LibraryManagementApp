package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    public static final String dbname ="library_online.db";
    public Dbhelper(Context context) {
        super(context, dbname, null, 1);
    }
    public static final String qry2 = "CREATE TABLE lib_book (bname text primary key, author text,genre text, url text)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(qry2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists lib_book");
        onCreate(db);

    }
    public Boolean inserto(String bname, String aname,String ge,String url){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put("bname",bname);
        cvs.put("author",aname);
        cvs.put("genre",ge);
        cvs.put("url",url);
        long res = db.insert("lib_book",null,cvs);
        if(res==-1)
        {
            return false;
        }
        else
            return true;
    }

    public Cursor getData1(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select bname,author from lib_book where genre = \"Thriller\"",null);
        return data;
    }
    public Cursor getData2(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select bname,author from lib_book where genre = \"Fiction\"",null);
        return data;
    }
    public Cursor getData3(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select bname,author from lib_book where genre = \"Romance\"",null);
        return data;
    }
    public Cursor getData4(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select bname,author from lib_book where genre = \"Sci-fi\"",null);
        return data;
    }
    public Cursor searchData(String search, String search_by){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select bname,author from lib_book where "+search_by+" like\"%"+search+"%\""+"",null);
        return data;
    }
    public Cursor getURL( String bname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select url from lib_book where bname =?",new String[]{bname});
        return data;
    }
    public Cursor getData( String bname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select bname,author,url from lib_book where bname =?",new String[]{bname});
        return data;
    }

}
