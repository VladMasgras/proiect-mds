package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AlegeCursa extends AppCompatActivity {

    ListView listView;

    ArrayList<Tren> query;

    public ArrayList<Tren> querymethod(List<Tren> trenuri){
        ArrayList<Tren> getTrains = new ArrayList<Tren>();
        for (Tren tren: trenuri){
            getTrains.add(new Tren(tren));
        }
        Log.d("",""+getTrains);

        return getTrains;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alege_cursa);

        Log.d("am ajuns", "ajuns");

        Bundle extras = getIntent().getExtras();
        //String orasPlecare = extras.getString("plecare");
        //String orasDestinatie = extras.getString("destinatie");
        final ArrayList<Tren> trenuri = new ArrayList<Tren>();


        listView = (ListView)findViewById(R.id.listviewCursa);
        List<Tren> arrayList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("trenuri");
        ref.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot trenSnapshot: dataSnapshot.getChildren()){
                    Tren tren = trenSnapshot.getValue(Tren.class);
                    Log.d("tren", ""+tren.getStatiePlecare());
                    trenuri.add(tren);
                    Log.d("trenuri", ""+ trenuri.get(0));
                }
                query = querymethod(trenuri);
                //TrenuriAdapter trenuriAdapter = new TrenuriAdapter((Context)this, trenuri);
                //ArrayAdapter<Tren> arrayAdapter = new ArrayAdapter<Tren>(this, android.R.layout.simple_list_item_1, trenuri);
                //listView.setAdapter(trenuriAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        List<Tren> queryTren = new ArrayList<Tren>();
        Log.d("query global", "" + query);
        ArrayList<Tren> arrayTrenuri = extras.getParcelableArrayList("arrayTrenuri");
        Log.d("arrayTrenuri", "" + arrayTrenuri);

        TrenuriAdapter trenuriAdapter = new TrenuriAdapter((Context)this, arrayTrenuri);
        //ArrayAdapter<Tren> arrayAdapter = new ArrayAdapter<Tren>(this, android.R.layout.simple_list_item_1, trenuri);
        listView.setAdapter(trenuriAdapter);

        /*for (Tren tren: query){
            Log.d("querystatie",""+tren.getStatiePlecare());
            //Log.d("querystatie2",""+orasPlecare);
            if (tren.getStatiePlecare().toLowerCase().equals(orasPlecare.toLowerCase()) && tren.getStatieSosire().toLowerCase().equals(orasDestinatie.toLowerCase()))
            {
                queryTren.add(tren);
                Log.d("query","we");
            }
        }
        Log.d("queryTren",""+ queryTren);*/
    }
}
