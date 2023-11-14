package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    LottieAnimationView animationView;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        animationView = findViewById(R.id.lottieAnimationView);

        // Start the animation
        animationView.playAnimation();
        TextView create = (TextView) findViewById(R.id.tv2);
        TextView forgot = (TextView) findViewById(R.id.tv1);
        Button login = (Button) findViewById(R.id.login);
        EditText mail = (EditText) findViewById(R.id.email);
        EditText pass = (EditText) findViewById(R.id.pass);

        create.setOnClickListener(v -> {
            Intent cre = new Intent(com.example.app.login.this, cracc.class);
            startActivity(cre);
        });

        forgot.setOnClickListener(v -> {
            Intent forgot1 = new Intent(com.example.app.login.this, com.example.app.forgot.class);
            startActivity(forgot1);
            finish();
        });

        login.setOnClickListener(v -> {
            String email, password;
            email = String.valueOf(mail.getText());
            password = String.valueOf(pass.getText());

            if(TextUtils.isEmpty(email)){
                Toast.makeText(login.this, "Enter mail id", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Sign-in successful
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i3 = new Intent(login.this, Homepage.class);
                            startActivity(i3); // Start the new activity after successful login
                            finish(); // Close the current login activity if necessary
                        } else {
                            // Sign-in failed
                            Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }

}