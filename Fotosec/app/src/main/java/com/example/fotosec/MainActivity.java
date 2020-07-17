package com.example.fotosec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    EditText idNumber;
    Button loginButton;
    EditText userName;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idNumber=findViewById(R.id.idNumber);
        userName=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginButton=findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference();
                myRef.child("users").child(idNumber.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Users kullanıcı=dataSnapshot.getValue(Users.class);
                        if (userName.getText().toString().equals(kullanıcı.getName())&& password.getText().toString().equals(kullanıcı.getPassword()) )
                        {
                            Intent intent = new Intent(MainActivity.this,photo.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "şifre yanlış", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("SADA");
                        System.out.println("SADA");
                    }
                });

            }
        });
    }
}
