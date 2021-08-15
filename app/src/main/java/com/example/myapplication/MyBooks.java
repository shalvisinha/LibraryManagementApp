package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyBooks extends AppCompatActivity {

    itemAdapter ItemAdapter;
    Context thisContext;
    ListView mybooklist;
    TextView heading;
    Dbhelper db;
    Map<String,String> bmap = new LinkedHashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        Resources res = getResources();
        mybooklist = (ListView)findViewById(R.id.list_mybooks);
        heading=(TextView)findViewById(R.id.headin);
        thisContext=this;
        db= new Dbhelper(MyBooks.this);
    }
}