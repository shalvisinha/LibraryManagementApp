package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main3 extends AppCompatActivity {

    itemAdapter ItemAdapter;
    Context thisContext;
    ListView mylist;
    TextView title;
    Dbhelper db;
    Map<String,String> bmap = new LinkedHashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final String nom = getIntent().getExtras().getString("com.example.myapplication.carry");
        Resources res = getResources();
        mylist = (ListView)findViewById(R.id.list);
        title=(TextView)findViewById(R.id.title);
        thisContext=this;
        db= new Dbhelper(Main3.this);
        if (getIntent().hasExtra("com.example.myapplication.m"))
        {
            int u = getIntent().getIntExtra("com.example.myapplication.m",0);
            if(u==1) {
                title.setText("THRILLER");
                Cursor data = db.getData1();
                while (data.moveToNext()) {
                    bmap.put(data.getString(0), data.getString(1));
                    ItemAdapter = new itemAdapter(thisContext, bmap);
                    mylist.setAdapter(ItemAdapter);
                }
            }
            else if(u==2){
                title.setText("FICTION");
                Cursor data = db.getData2();
                while (data.moveToNext()) {
                    bmap.put(data.getString(0), data.getString(1));
                    ItemAdapter = new itemAdapter(thisContext, bmap);
                    mylist.setAdapter(ItemAdapter);
                }
            }
            else if(u==3){
                title.setText("ROMANCE");
                Cursor data = db.getData3();
                while (data.moveToNext()) {
                    bmap.put(data.getString(0), data.getString(1));
                    ItemAdapter = new itemAdapter(thisContext, bmap);
                    mylist.setAdapter(ItemAdapter);
                }
            }
            else if(u==4){
                title.setText("SCI-FI");
                Cursor data = db.getData4();
                while (data.moveToNext()) {
                    bmap.put(data.getString(0), data.getString(1));
                    ItemAdapter = new itemAdapter(thisContext, bmap);
                    mylist.setAdapter(ItemAdapter);
                }
            }

        }
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = mylist.getItemAtPosition(position).toString();
                Intent intent= new Intent(getApplicationContext(),PDFview.class);
                intent.putExtra("com.example.myapplication.s", s);
                startActivity(intent);
            }
        });
    }

        }

