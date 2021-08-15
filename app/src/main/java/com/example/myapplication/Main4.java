package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Main4 extends AppCompatActivity {
    Dbhelper db;
    EditText bname;
    EditText aname;
    EditText genre;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        db = new Dbhelper(Main4.this);
        storageReference = FirebaseStorage.getInstance().getReference();
        Button add = (Button)findViewById(R.id.button);
        bname=(EditText)findViewById(R.id.bname);
        aname= (EditText)findViewById(R.id.aname);
        genre = (EditText)findViewById(R.id.genre);
        Button btn_upload = (Button)findViewById(R.id.upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDFFile();
            }
        });


    }

    private void selectPDFFile() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode== RESULT_OK && data != null && data.getData() != null){
            uploadPDFFile(data.getData());
        }
    }

    private void uploadPDFFile(Uri data) {

        StorageReference reference = storageReference.child("uploads/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url = uri.getResult();
                        new Dbhelper(Main4.this);
                        Boolean inserto = db.inserto(bname.getText().toString(),aname.getText().toString(),genre.getText().toString(),url.toString());
                        if (inserto == true) {
                            Toast.makeText(Main4.this, "record added!!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Main4.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Main4.this, "uploaded!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Main4.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
