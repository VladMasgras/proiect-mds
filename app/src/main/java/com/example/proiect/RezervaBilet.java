package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RezervaBilet extends AppCompatActivity {

    List<Tren> query;

    public List<Tren> querymethod(List<Tren> trenuri){
        ArrayList<Tren> getTrains = new ArrayList<Tren>();
        for (Tren tren: trenuri){
            getTrains.add(tren);
        }
        Log.d("",""+getTrains);

        return getTrains;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezerva_bilet);

        final EditText orasPlecareText = findViewById(R.id.orasPlecare);
        final EditText orasDestinatieText = findViewById(R.id.orasDestinatie);

        final Button button = findViewById(R.id.button3);

        final List<Tren> trenuri = new ArrayList<Tren>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("trenuri");
        ref.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot trenSnapshot: dataSnapshot.getChildren()){
                    Tren tren = trenSnapshot.getValue(Tren.class);
                    //Log.d("tren", ""+tren.getStatiePlecare());
                    trenuri.add(tren);
                    //Log.d("trenuri", ""+ trenuri.get(0));
                    query = querymethod(trenuri);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RezervaBilet.this, AlegeCursa.class);
                String orasPlecare = orasPlecareText.getText().toString();
                String orasDestinatie = orasDestinatieText.getText().toString();
                List<Tren> queryTren = new ArrayList<Tren>();
                            for (Tren tren: query){
                                Log.d("querystatie",""+tren.getStatiePlecare());
                                //Log.d("querystatie2",""+orasPlecare);
                                if (tren.getStatiePlecare().toLowerCase().equals(orasPlecare.toLowerCase()) && tren.getStatieSosire().toLowerCase().equals(orasDestinatie.toLowerCase()))
                                {
                                    queryTren.add(tren);
                                    Log.d("query","we");
                                }
                            }
                Log.d("queryTren",""+ queryTren);
                intent.putParcelableArrayListExtra("arrayTrenuri", (ArrayList<? extends Parcelable>) queryTren);
                startActivity(intent);
            }
        });
    }
}
