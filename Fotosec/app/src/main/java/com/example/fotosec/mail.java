package com.example.fotosec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class mail extends AppCompatActivity {
    public EditText mEmail,mSubject;
    Button buttonmail;
    String deger1,deger2,deger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null){
            deger1 = bundle1.getString("foto1");
        }
        Bundle bundle2 = getIntent().getExtras();
        if(bundle2 != null){
            deger2 = bundle2.getString("foto2");
        }
       System.out.println(deger1+deger2);


        /* if(deger1=="1-" || deger2==null) {
            deger=deger1;
        }
        if(deger1=="1-efekt_eklendi/" || deger2==null) {
            deger=deger1;
        }
        if(deger1==null || deger2=="2-") {
            deger=deger2;
        }
        if(deger1==null || deger2=="2-efekt_eklendi/") {
            deger=deger+deger2;
        }
        else{
            deger=deger1;
            deger=deger+deger2;
        }*/

       deger=deger1+deger2;



        System.out.println(deger);
        mEmail=(EditText)findViewById(R.id.mailID);
       // mMessage.setText(deger);
        mSubject=(EditText)findViewById(R.id.subjectID);

        buttonmail =(Button)findViewById(R.id.buttonMail);
        buttonmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });


    }



    private void sendMail() {
        /*String mail=mEmail.getText().toString().trim();
        String message=mMessage.getText().toString().trim();
        String subject=mSubject.getText().toString().trim();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);
        javaMailAPI.execute();
        */

        String recipientList = mEmail.getText().toString();
        String[] recipients= recipientList.split(",");
       // String message=mMessage.getText().toString();
        String subject=mSubject.getText().toString();

        Intent intent =new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,deger);

        intent.setType("messaga/rfc822");
        startActivity(Intent.createChooser(intent,"Hangi Servisi Kullanmak Istiyorsaniz Secin...."));



    }
}
