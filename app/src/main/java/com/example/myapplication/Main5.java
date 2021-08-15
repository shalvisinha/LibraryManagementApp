package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Main5 extends AppCompatActivity {
    Dbmanager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        TextView n = (TextView) findViewById(R.id.na);
        TextView p = (TextView) findViewById(R.id.nu);
        db= new Dbmanager(this);
        String name_log = getIntent().getExtras().getString("com.example.myapplication.some");
        String ph_log = getIntent().getExtras().getString("com.example.myapplication.ph");
        n.setText(name_log);
        p.setText(ph_log);
        Button change_name = (Button)findViewById(R.id.change1);
        change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_dialog1(v);
            }
        });
        Button change_phone = (Button)findViewById(R.id.change2);
        change_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_dialog2(v);
            }
        });
    }

    public void show_dialog1(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Main5.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog1, null);
        final EditText new_name = mView.findViewById(R.id.edit_name);
        Button ok_name = mView.findViewById(R.id.ok_namechange);
        Button cancel_name = mView.findViewById(R.id.cancel_namechange);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        cancel_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ok_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nname = new_name.getText().toString();
                TextView n = (TextView) findViewById(R.id.na);
                TextView p = (TextView) findViewById(R.id.nu);
                String ph = p.getText().toString();
                n.setText(nname);
                Boolean change = db.change(nname,ph);
                if(change==true)
                    Toast.makeText(Main5.this,"Successfully Changed",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Main5.this,"Unsuccessful",Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void show_dialog2(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Main5.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog2, null);
        final EditText new_phone = mView.findViewById(R.id.new_ph);
        Button ok_ph = mView.findViewById(R.id.ok_ph);
        Button cancel_name = mView.findViewById(R.id.cancel_ph);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        cancel_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ok_ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nphone = new_phone.getText().toString();
                TextView p = (TextView) findViewById(R.id.nu);
                String ph = p.getText().toString();
                Boolean change = db.change_ph(nphone,ph);
                p.setText(nphone);
                if(change==true)
                    Toast.makeText(Main5.this,"Successfully Changed",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Main5.this,"Unsuccessful",Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
