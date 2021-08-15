package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class search extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    itemAdapter ItemAdapter;
    Context thisContext;
    ListView searchlist;
    Dbhelper db;
    Map<String,String> bmap = new LinkedHashMap<String, String>();
    String set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Resources res = getResources();
        searchlist = (ListView)findViewById(R.id.searchlist);
        thisContext=search.this;
        db= new Dbhelper(search.this);
        Spinner sp = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.search_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(search.this);
        Button search_go = (Button)findViewById(R.id.search_go);
        search_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText se = (EditText)findViewById(R.id.search_this);
                String search = se.getText().toString();
                String search_by = rec();
                Cursor data = db.searchData(search,search_by);
                bmap.clear();
                while (data.moveToNext()) {
                    bmap.put(data.getString(0), data.getString(1));
                    ItemAdapter = new itemAdapter(thisContext, bmap);
                    searchlist.setAdapter(ItemAdapter);
                    se.setText("");
                }


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
               set = "bname";
               break;
            case 1:
                set = "author";
                break;
        }
            }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public String rec(){
        return set;
    }
}
