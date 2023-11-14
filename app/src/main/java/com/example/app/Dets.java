package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Dets extends AppCompatActivity {
    public static final String NUM = "Number";

    String nm, con, ap, st, lan;
    FirebaseDatabase db;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dets);

        EditText name = (EditText) findViewById(R.id.name);
        EditText contact = (EditText) findViewById(R.id.contact);
        EditText apart = (EditText) findViewById(R.id.apart);
        EditText street = (EditText) findViewById(R.id.street);
        EditText landmark = (EditText) findViewById(R.id.land);
        Button save = (Button) findViewById(R.id.savee);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm = name.getText().toString();
                con = contact.getText().toString();
                ap = apart.getText().toString();
                st = street.getText().toString();
                lan = landmark.getText().toString();



                if(!nm.isEmpty() && !con.isEmpty() && !ap.isEmpty() && !st.isEmpty()){
                     users us = new users(nm, con, ap, st, lan);
                     db = FirebaseDatabase.getInstance();
                     reference = db.getReference("User");
                     reference.child(nm).setValue(us).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             name.setText("");
                             contact.setText("");
                             apart.setText("");
                             street.setText("");
                             landmark.setText("");
                             Toast.makeText(Dets.this, "Details Saved", Toast.LENGTH_SHORT).show();
                             Intent i = new Intent(Dets.this, Homepage.class);
                             startActivity(i);

                         }

                     });
                }
            }
        });


    }
}