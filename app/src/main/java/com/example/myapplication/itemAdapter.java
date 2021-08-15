package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class itemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    Map<String,String> map;
    List<String> bname;
    List<String> author;
    Dbhelper db;
    Context thisContext;

    public itemAdapter(Context c, Map m){
        mInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        map=m;
        bname= new ArrayList<String>(map.keySet());
        author=new ArrayList<String>(map.values());
    }
    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public Object getItem(int position) {
        return bname.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.lay,null);
        thisContext=(Activity)v.getContext();
        db= new Dbhelper(thisContext);
        TextView name = (TextView)v.findViewById(R.id.name);
        TextView auth = (TextView) v.findViewById(R.id.author);
        final String nom = ((Activity)v.getContext()).getIntent().getExtras().getString("com.example.myapplication.carry");
        Button addto = (Button)v.findViewById(R.id.addto);
        final String s = bname.get(position);
        name.setText(bname.get(position));
        auth.setText(author.get(position));
        addto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Boolean insert = db.inserts(s,nom);
                if (insert == true) {
                    Toast.makeText(v.getContext(), s+"," +nom+ "added to my books!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(v.getContext(), "failed", Toast.LENGTH_SHORT).show();
                }*/
                //Toast.makeText( v.getContext() ,s+" yeahh "+nom, Toast.LENGTH_SHORT).show();
                //Toast.makeText(v.getContext(),"Added to \'My Books\'",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

}
