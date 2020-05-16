package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdaugaTren extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_tren);

        final EditText serieText = findViewById(R.id.serie);
        final EditText statplecText = findViewById(R.id.statplec);
        final EditText oraplecText = findViewById(R.id.oraplec);
        final EditText statsosText = findViewById(R.id.statsos);
        final EditText orasosText = findViewById(R.id.orasos);
        final EditText distantaText = findViewById(R.id.distanta);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int serie = Integer.parseInt(serieText.getText().toString());
                String statiePlecare = statplecText.getText().toString();
                String oraPlecare = oraplecText.getText().toString();
                String statieSosire = statsosText.getText().toString();
                String oraSosire = orasosText.getText().toString();
                int distanta = Integer.parseInt(distantaText.getText().toString());

                Tren tren = new Tren(serie,distanta,statiePlecare,oraPlecare,statieSosire,oraSosire);
                Log.d("tren","" + tren);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("trenuri");
                ref.child("" + tren.getSerie()).setValue(tren);

                serieText.getText().clear();
                statplecText.getText().clear();
                oraplecText.getText().clear();
                statsosText.getText().clear();
                orasosText.getText().clear();
                distantaText.getText().clear();
            }
        });

    }
}
