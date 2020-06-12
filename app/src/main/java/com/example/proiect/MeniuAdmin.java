package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MeniuAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu_admin);

        Button btnAddTren = findViewById(R.id.admin_adauga_tren);
        Button btnAddVagon = findViewById(R.id.admin_adauga_vagon);
        Button btnLogout = findViewById(R.id.btnlogout);

        btnAddTren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeniuAdmin.this, AdaugaTren.class);
                startActivity(intent);
            }
        });

        btnAddVagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeniuAdmin.this, AdaugaVagoane.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MeniuAdmin.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
