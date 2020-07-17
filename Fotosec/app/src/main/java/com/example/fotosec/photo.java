package com.example.fotosec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class photo extends AppCompatActivity {
    CheckBox c1,c2;
    String foto1=null,foto2=null;//secilen icin
    String btn1=null,btn2=null; //duzen butonu icin
    Button button, button2,button3,buttongonder;
    ImageView imageView;
    ImageView imageView2;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        buttongonder=findViewById(R.id.buttongonder);
        Bundle bundlef = getIntent().getExtras();


        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
                download2();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1="efekt_eklendi/";
               imageView.setColorFilter(Color.DKGRAY, PorterDuff.Mode.LIGHTEN);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2="efekt_eklendi/";
                imageView2.setColorFilter(Color.DKGRAY, PorterDuff.Mode.LIGHTEN);
            }
        });


        c1=findViewById(R.id.checkBox1);
        c2=findViewById(R.id.checkBox2);

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                foto1= "1-";
                 if (btn1=="efekt_eklendi/"){
                     foto1=foto1+btn1;
                 }
                    System.out.println(foto1);
            }
        });

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                   foto2= "2-";
                if (btn2=="efekt_eklendi/"){
                    foto2=foto2+btn2;
                }
                System.out.println(foto2);
            }
        });


        buttongonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmail();
            }
        });



    }

    private void download() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref=storageReference.child("gorseller");
        StorageReference reference2=ref.child("yenifoto.jpg");
        reference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                Picasso.with(getApplicationContext()).load(url).into(imageView);
                System.out.println("Goruntu Geldi");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
             }
        });
    }

    private void download2() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref=storageReference.child("gorseller");
        StorageReference reference2=ref.child("yenifoto2.jpg");
        reference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url=uri.toString();
                Picasso.with(getApplicationContext()).load(url).into(imageView2);
                System.out.println("Görüntü 2 Geldi");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }



    public void openmail(){
        if (foto1==null || foto2==null) {
            Toast.makeText(this, "Fotograf Secilmedi !!! ", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(this,mail.class);
            intent.putExtra("foto1",foto1);
            intent.putExtra("foto2",foto2);
            startActivity(intent);
        }

    }







}
