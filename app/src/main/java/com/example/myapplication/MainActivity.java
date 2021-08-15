package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Dbmanager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new Dbmanager(this);
        final RadioButton rb1= (RadioButton)findViewById(R.id.student);
        final RadioButton rb2 = (RadioButton)findViewById(R.id.employee);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
        final Button log = (Button)findViewById(R.id.log);
        EditText pass =findViewById(R.id.password);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.editText);
                EditText editText2 = (EditText)findViewById(R.id.editText2);
                String name = editText.getText().toString();
                String s2 = editText2.getText().toString();
                new Dbmanager(MainActivity.this);
                if(TextUtils.isEmpty(editText.getText().toString()))
                {
                    editText.setError("Cannot be empty");
                    Intent start = new Intent(getApplicationContext(),MainActivity.class);
                }
                else if (TextUtils.isEmpty(editText2.getText().toString()) | editText2.length()!=10)
                {
                    editText2.setError("please enter a valid phone number");
                    Intent start = new Intent(getApplicationContext(),MainActivity.class);
                }
                else {
                    int sym = 0;
                    if (rb1.isChecked()) {
                        sym = 1;
                    } else if (rb2.isChecked()) {
                        sym = 2;
                    }
                    Boolean check = db.check(s2);
                    if(check==true)
                        {
                        Toast.makeText(MainActivity.this,"Sorry, you are not registered!please signUp first!!",Toast.LENGTH_SHORT).show();
                        }
                    else {
                        if(sym==1) {
                            Intent start = new Intent(getApplicationContext(), Main2Activity.class);
                            start.putExtra("com.example.myapplication.some", name);
                            start.putExtra("com.example.myapplication.ph", s2);
                            start.putExtra("com.example.myapplication.more", sym);
                            startActivity(start);
                        }
                        else
                        {
                            show_dialog(v);
                        }
                    }
                }

            }
        });

        Button sign= (Button)findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)findViewById(R.id.editText);
                EditText editText2 = (EditText)findViewById(R.id.editText2);
                String s1 = editText.getText().toString();
                String s2 = editText2.getText().toString();
                Boolean check = db.check(s2);
                if(check== true) {
                    Boolean insert = db.insert(s1, s2);
                    if (insert == true) {
                        Toast.makeText(MainActivity.this, "Congratulations!!your account has been created.Please login", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Account already exist!!", Toast.LENGTH_SHORT).show();
                    }
                }

        });
        Button exit = (Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


    }
    public void show_dialog(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View mview = getLayoutInflater().inflate(R.layout.dialog,null);
        final EditText pass = mview.findViewById(R.id.password);
        Button ok = mview.findViewById(R.id.ok);
        Button cancel = mview.findViewById(R.id.cancel);
        alert.setView(mview);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
         cancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                        alertDialog.dismiss();
             }
         });
         ok.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String check = pass.getText().toString();
                 EditText editText = (EditText)findViewById(R.id.editText);
                 EditText editText2 = (EditText)findViewById(R.id.editText2);
                 TextView txt = findViewById(R.id.textView3);
                 final RadioButton rb1= (RadioButton)findViewById(R.id.student);
                 final RadioButton rb2 = (RadioButton)findViewById(R.id.employee);
                 String name = editText.getText().toString();
                 String s2 = editText2.getText().toString();
                 int sym = 0;
                 if (rb1.isChecked()) {
                     sym = 1;
                 } else if (rb2.isChecked()) {
                     sym = 2;
                 }
                 if( check.equals("shalvi")) {
                     Intent start = new Intent(getApplicationContext(), Main2Activity.class);
                     start.putExtra("com.example.myapplication.some", name);
                     start.putExtra("com.example.myapplication.ph", s2);
                     start.putExtra("com.example.myapplication.more", sym);
                     startActivity(start);
                 }
                 else{
                     Toast.makeText(MainActivity.this,"Incorect password",Toast.LENGTH_SHORT).show();
                 }
                 alertDialog.dismiss();
             }
         });
         alertDialog.show();


    }
}
