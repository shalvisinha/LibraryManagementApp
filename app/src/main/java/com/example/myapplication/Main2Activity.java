package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView tv;
    TextView show;
    Button add;
    int cat;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        navigationView= findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        show = findViewById(R.id.so);
        final String t;



        if (getIntent().hasExtra("com.example.myapplication.some"))
        {
            tv = (TextView)findViewById(R.id.tv);
            String r = "Welcome, ";
            t = getIntent().getExtras().getString("com.example.myapplication.some");
            String text = r+t;
            tv.setText(text);
            show.setText(t);

        }
        if (getIntent().hasExtra("com.example.myapplication.more"))
        {
            add = (Button) findViewById(R.id.add);
            int user = getIntent().getIntExtra("com.example.myapplication.more",0);
            if(user==1)
            {
                add.setVisibility(View.INVISIBLE);
            }

        }
        ImageButton thrill = (ImageButton)findViewById(R.id.thrill);
        thrill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat=1;
                String t = getIntent().getExtras().getString("com.example.myapplication.some");
                Intent startId = new Intent(getApplicationContext(),Main3.class);
                startId.putExtra("com.example.myapplication.m", cat);
                startId.putExtra("com.example.myapplication.carry",t);
                startActivity(startId);
            }
        });
        ImageButton fiction = (ImageButton)findViewById(R.id.fic);
        fiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat=2;
                String t = getIntent().getExtras().getString("com.example.myapplication.some");
                Intent startId = new Intent(getApplicationContext(),Main3.class);
                startId.putExtra("com.example.myapplication.m", cat);
                startId.putExtra("com.example.myapplication.carry",t);
                startActivity(startId);
            }
        });
        ImageButton romance = (ImageButton)findViewById(R.id.rom);
        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat=3;
                String t = getIntent().getExtras().getString("com.example.myapplication.some");
                Intent startId = new Intent(getApplicationContext(),Main3.class);
                startId.putExtra("com.example.myapplication.m", cat);
                startId.putExtra("com.example.myapplication.carry",t);
                startActivity(startId);
            }
        });
        ImageButton scifi = (ImageButton)findViewById(R.id.sci);
        scifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cat=4;
                String t = getIntent().getExtras().getString("com.example.myapplication.some");
                Intent startId = new Intent(getApplicationContext(),Main3.class);
                startId.putExtra("com.example.myapplication.m", cat);
                startId.putExtra("com.example.myapplication.carry",t);
                startActivity(startId);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startId = new Intent(getApplicationContext(),Main4.class);
                startActivity(startId);
            }
        });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem items) {

        String nam = getIntent().getExtras().getString("com.example.myapplication.some");
        String ph = getIntent().getExtras().getString("com.example.myapplication.ph");
        switch (items.getItemId()){
            case R.id.profile:
                Intent star = new Intent(Main2Activity.this, Main5.class);
                star.putExtra("com.example.myapplication.some", nam);
                star.putExtra("com.example.myapplication.ph",ph);
                startActivity(star);
                break;
            case R.id.about:
                Toast.makeText(this,"about is clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Search:
                Intent startsearch = new Intent(Main2Activity.this,search.class);
                startActivity(startsearch);
                break;
            case R.id.contact_us:
                Intent startcontact = new Intent(Main2Activity.this,contact.class);
                startActivity(startcontact);
                break;
            case R.id.logout:
                Intent logout = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(logout);
                break;
            case R.id.books:

        }
        return true;
    }

}
