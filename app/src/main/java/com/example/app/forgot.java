package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {
    public FirebaseAuth mAuth;
    LottieAnimationView animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        mAuth = FirebaseAuth.getInstance();

        EditText mail = (EditText) findViewById(R.id.mail1);
        Button reset = (Button) findViewById(R.id.reset);
        ImageButton back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(forgot.this, login.class);
                startActivity(i);
            }
        });

        reset.setOnClickListener(v -> {
            String email;
            email = String.valueOf(mail.getText());
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter your email address", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Password reset email sent", Toast.LENGTH_SHORT).show();
                            Intent b = new Intent(forgot.this, login.class);
                            startActivity(b);
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to send password reset email", Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }
}