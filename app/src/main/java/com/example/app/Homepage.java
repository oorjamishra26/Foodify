package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
    ListView myListView;
    ArrayList<String> myarraylist = new ArrayList<>();
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ImageView sugar = (ImageView) findViewById(R.id.sugar);
        ImageView sav = (ImageView) findViewById(R.id.sav);
        ImageView fry = (ImageView) findViewById(R.id.fry);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView userEmailTextView = headerView.findViewById(R.id.userEmail); // Replace with your TextView ID
        String userEmail = "";// Replace with the actual user's email
        String username = "";
        String usernumber = "";
        userEmailTextView.setText(userEmail);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            userEmail = currentUser.getEmail();
            userEmailTextView.setText(userEmail);
        }

        Button openDrawerButton = findViewById(R.id.openDrawerButton);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout); // Assuming you have a DrawerLayout

        openDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        sugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sug = new Intent(Homepage.this, Sugar.class);
                startActivity(sug);
            }
        });
        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sav = new Intent(Homepage.this, savour.class);
                startActivity(sav);
            }
        });
        fry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fr = new Intent(Homepage.this, com.example.app.fry.class);
                startActivity(fr);
            }
        });

    }
}