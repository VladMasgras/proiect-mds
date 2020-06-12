package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MeniuUtilizator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu_utilizator);

        Button rBilet = findViewById(R.id.btnRezervaBilet);
        Button vBilete = findViewById(R.id.btnVeziBilete);
        Button logout = findViewById(R.id.btnLogout);

        rBilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeniuUtilizator.this, RezervaBilet.class);
                startActivity(intent);
            }
        });

        vBilete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeniuUtilizator.this, VeziBileteRezervate.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MeniuUtilizator.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
