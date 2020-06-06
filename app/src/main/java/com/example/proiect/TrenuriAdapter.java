package com.example.proiect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TrenuriAdapter extends ArrayAdapter<Tren> {
    private Context mContext;
    private List<Tren> trenuriList = new ArrayList<>();

    public TrenuriAdapter(@NonNull Context context, ArrayList<Tren> list) {
        super(context, 0 , list);
        mContext = context;
        trenuriList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Tren currentTren = trenuriList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_plecare);
        name.setText(currentTren.getStatiePlecare());

        TextView release = (TextView) listItem.findViewById(R.id.textView_destinatie);
        release.setText(currentTren.getStatieSosire());

        TextView oraPlecare = (TextView) listItem.findViewById(R.id.oraplecare);
        oraPlecare.setText(currentTren.getOraPlecare());

        TextView oraSosire = (TextView) listItem.findViewById(R.id.orasosire);
        oraSosire.setText(currentTren.getOraSosire());

        return listItem;
    }
}
