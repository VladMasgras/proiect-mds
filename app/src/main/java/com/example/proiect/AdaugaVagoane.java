package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdaugaVagoane extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_vagoane);

        final EditText serieText = findViewById(R.id.serie);
        final EditText nrLocuriText = findViewById(R.id.nr_locuri);
        final EditText nrVagoaneText = findViewById(R.id.nr_vagoane);
        final EditText clasaText = findViewById(R.id.clasa);

        Button button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int serie = Integer.parseInt(serieText.getText().toString());
                int nrLocuri = Integer.parseInt(nrLocuriText.getText().toString());
                int nrVagoane = Integer.parseInt(nrVagoaneText.getText().toString());
                int clasa = Integer.parseInt(clasaText.getText().toString());

                DatabaseReference refVagoane = FirebaseDatabase.getInstance().getReference().child("vagoane");


                for (int i = 0; i < nrVagoane; i++) {

                    Vagon vagon = new Vagon(clasa, nrLocuri);
                    refVagoane.child("" + serie).push().setValue(vagon);

                }
                serieText.getText().clear();
                nrLocuriText.getText().clear();
                nrVagoaneText.getText().clear();
                clasaText.getText().clear();
            }
        });


    }
}
