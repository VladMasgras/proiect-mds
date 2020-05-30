package com.example.proiect;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Tren implements Parcelable {
    private int serie,distanta;
    private String statiePlecare,oraPlecare,statieSosire,oraSosire;

    public Tren(){

    }

    public Tren(Tren tren){
        this.serie = tren.serie;
        this.distanta = tren.distanta;
        this.statiePlecare = tren.statiePlecare;
        this.oraPlecare = tren.oraPlecare;
        this.statieSosire = tren.statieSosire;
        this.oraSosire = tren.oraSosire;
    }

    public Tren(int serie, int distanta, String statiePlecare, String oraPlecare, String statieSosire, String oraSosire) {
        this.serie = serie;
        this.distanta = distanta;
        this.statiePlecare = statiePlecare;
        this.oraPlecare = oraPlecare;
        this.statieSosire = statieSosire;
        this.oraSosire = oraSosire;
    }


    protected Tren(Parcel in) {
        serie = in.readInt();
        distanta = in.readInt();
        statiePlecare = in.readString();
        oraPlecare = in.readString();
        statieSosire = in.readString();
        oraSosire = in.readString();
    }

    public static final Creator<Tren> CREATOR = new Creator<Tren>() {
        @Override
        public Tren createFromParcel(Parcel in) {
            return new Tren(in);
        }

        @Override
        public Tren[] newArray(int size) {
            return new Tren[size];
        }
    };

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getDistanta() {
        return distanta;
    }

    public void setDistanta(int distanta) {
        this.distanta = distanta;
    }

    public String getStatiePlecare() {
        return statiePlecare;
    }

    public void setStatiePlecare(String statiePlecare) {
        this.statiePlecare = statiePlecare;
    }

    public String getOraPlecare() {
        return oraPlecare;
    }

    public void setOraPlecare(String oraPlecare) {
        this.oraPlecare = oraPlecare;
    }

    public String getStatieSosire() {
        return statieSosire;
    }

    public void setStatieSosire(String statieSosire) {
        this.statieSosire = statieSosire;
    }

    public String getOraSosire() {
        return oraSosire;
    }

    public void setOraSosire(String oraSosire) {
        this.oraSosire = oraSosire;
    }

    @Override
    public String toString(){
        return statiePlecare + " " + statieSosire;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(serie);
        parcel.writeInt(distanta);
        parcel.writeString(statiePlecare);
        parcel.writeString(oraPlecare);
        parcel.writeString(statieSosire);
        parcel.writeString(oraSosire);
    }
}
