package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AlegeCursa extends AppCompatActivity {

    ListView listView;
    boolean succes = false;
    private FirebaseAuth mAuth;
    private ArrayList<Pair<String, Integer>> vagoane = new ArrayList<Pair<String, Integer>>();
    private ArrayList<Loc> locuri;

    ArrayList<Tren> query;
    boolean ocupat = false;
    int ultimulLocDat = 0;

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

        mAuth = FirebaseAuth.getInstance();

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
        final ArrayList<Tren> arrayTrenuri = extras.getParcelableArrayList("arrayTrenuri");
        Log.d("arrayTrenuri", "" + arrayTrenuri);

        final FirebaseUser user = mAuth.getCurrentUser();

        TrenuriAdapter trenuriAdapter = new TrenuriAdapter((Context)this, arrayTrenuri);
        //ArrayAdapter<Tren> arrayAdapter = new ArrayAdapter<Tren>(this, android.R.layout.simple_list_item_1, trenuri);
        listView.setAdapter(trenuriAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Tren tren = arrayTrenuri.get(i);
                final DatabaseReference refVagon = FirebaseDatabase.getInstance().getReference().child("vagoane").child("" + tren.getSerie());
                refVagon.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot dsp : dataSnapshot.getChildren()){

                            locuri = new ArrayList<Loc>();
                            Log.d("id vagon", "" + dsp.getRef());
                            final int nrVagon = ((Long)dsp.child("nrVagon").getValue()).intValue();
                            final int nrLocuri = ((Long)dsp.child("nrLocuri").getValue()).intValue();
                            int index = dsp.getRef().toString().lastIndexOf('/');
                            final String idVagon = dsp.getRef().toString().substring(index + 1);
                            vagoane.add(new Pair<String, Integer>(idVagon, nrVagon));
                            final DatabaseReference refLoc = FirebaseDatabase.getInstance().getReference().child("locuri").child(idVagon);
                            refLoc.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (DataSnapshot dsp : dataSnapshot.getChildren()){
                                        Log.d("id loc", "" + dsp.child("available").getValue());
                                        if ((boolean) dsp.child("available").getValue()){
                                            dsp.getRef().child("available").setValue(Boolean.valueOf("false"));
                                            int nrLoc = ((Long)dsp.child("nrLoc").getValue()).intValue();
                                            ultimulLocDat = nrLoc;
                                            Bilet bilet = new Bilet(tren.getSerie(), tren.getDistanta(), nrVagon, nrLoc, tren.getStatiePlecare(), tren.getOraPlecare(), tren.getStatieSosire(), tren.getOraSosire());
                                            DatabaseReference refUseri = FirebaseDatabase.getInstance().getReference().child("useri");
                                            refUseri.child("" + user.getUid()).push().setValue(bilet);
                                            succes = true;
                                            break;
                                        }
                                        Loc loc = dsp.getValue(Loc.class);
                                        locuri.add(loc);

                                    }
                                    checkLoc();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            if (nrLocuri == ultimulLocDat) {
                                refVagon.child(idVagon).child("ocupat").setValue(true);
                                ultimulLocDat = 0;
                            }
                            else
                            if ((Boolean)dsp.child("ocupat").getValue() == false)
                                break;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                System.out.println(vagoane);
            }
        });



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
    void checkLoc(){
        if (succes == false)
            Toast.makeText(getApplicationContext(), "Nu mai sunt locuri", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Loc rezervat cu succes", Toast.LENGTH_SHORT).show();
    }
}
