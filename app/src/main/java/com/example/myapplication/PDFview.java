package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PDFview extends AppCompatActivity {
    Dbhelper db;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        db= new Dbhelper(PDFview.this);
        final ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        //progressDialog.setMessage("Loading Data...");
        //progressDialog.setCancelable(false);
        WebView web_view = findViewById(R.id.web_view);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setDomStorageEnabled(true);
        web_view.getSettings().setBuiltInZoomControls(true);
        if(getIntent().hasExtra("com.example.myapplication.s")) {
            String book_name = getIntent().getExtras().getString("com.example.myapplication.s");
            Cursor data = db.getURL(book_name);
            while (data.moveToNext()) {
                String url = data.getString(0);
                web_view.setWebChromeClient( new WebChromeClient() {
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                        if(newProgress==100){
                            progressBar.setVisibility(View.GONE);
                            //getSupportActionBar().setTitle(R.string.app_name);
                        }
                    }
                });
                String sturl="";
                try {
                    sturl=URLEncoder.encode(url,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String myurl = "http://drive.google.com/viewerng/viewer?embedded=true&url=" + sturl;
                web_view.loadUrl(myurl);
            }
                //Toast.makeText(getApplicationContext(),url+".pdf",Toast.LENGTH_LONG).show();
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                //startActivity(browserIntent);
        }
    }
}
