package com.example.proiect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1.Adauga tren");
        arrayList.add("2.Rezerva bilet");
        arrayList.add("3.Adauga vagoane");
        arrayList.add("4.Login");
        /*arrayList.add("4.Sterge doctor");
        arrayList.add("5.Afiseaza pacientii");
        arrayList.add("6.Afiseaza pacientii copii");
        arrayList.add("7.Afiseaza pacientii adulti");
        arrayList.add("8.Afiseaza pacientii batrani");
        arrayList.add("9.Afiseaza pacientii cu risc cardiovascular");
        arrayList.add("10.Afiseaza doctorii");
        arrayList.add("11.Adauga o reteta unui pacient");
        arrayList.add("12.Asigneaza un pacient unui doctor/dentist");
        arrayList.add("13.Adauga dentist");
        arrayList.add("Exit");
        */

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(MainActivity.this, AdaugaTren.class);
                    startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent(MainActivity.this, RezervaBilet.class);
                    startActivity(intent);
                }
                else if (position == 2){
                    Intent intent = new Intent(MainActivity.this, AdaugaVagoane.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }

            }
        });



    }
}
