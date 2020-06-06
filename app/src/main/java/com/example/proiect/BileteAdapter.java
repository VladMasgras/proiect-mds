package com.example.proiect;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class BileteAdapter extends ArrayAdapter<Bilet> {

    private Context mContext;
    private List<Bilet> bileteList = new ArrayList<>();

    public BileteAdapter(@NonNull Context context, ArrayList<Bilet> list){
        super(context, 0 , list);
        mContext = context;
        bileteList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_bilete,parent,false);

        listItem.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bilet));

        Bilet currentBilet = bileteList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_plecare);
        name.setText(currentBilet.getStatiePlecare());

        TextView release = (TextView) listItem.findViewById(R.id.textView_destinatie);
        release.setText(currentBilet.getStatieSosire());

        TextView oraPlecare = (TextView) listItem.findViewById(R.id.oraplecare);
        oraPlecare.setText(currentBilet.getOraPlecare());

        TextView oraSosire = (TextView) listItem.findViewById(R.id.orasosire);
        oraSosire.setText(currentBilet.getOraSosire());

        TextView loc = (TextView) listItem.findViewById(R.id.loc);
        loc.setText(Integer.toString(currentBilet.getLoc()));

        TextView vagon = (TextView) listItem.findViewById(R.id.vagon);
        vagon.setText(Integer.toString(currentBilet.getVagon()));

        TextView locText = listItem.findViewById(R.id.loc_edit);
        locText.setText("Loc");

        TextView vagonText = listItem.findViewById(R.id.vagon_edit);
        vagonText.setText("Vagon");

        return listItem;
    }
}
