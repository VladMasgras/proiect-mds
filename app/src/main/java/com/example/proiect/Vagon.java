package com.example.proiect;

import java.util.ArrayList;
import java.util.List;

public class Vagon {
    private int clasa,nrLocuri, nrVagon;
    boolean ocupat;
    private List<Loc> locuri;

    public Vagon(){}

    public Vagon(int clasa, int nrLocuri, int nrVagon, boolean ocupat){
        this.clasa = clasa;
        this.nrLocuri = nrLocuri;
        this.nrVagon = nrVagon;
        this.ocupat = ocupat;
        locuri = new ArrayList<Loc>(nrLocuri);
    }

    public int getNrVagon(){ return nrVagon; }

    public int getClasa() {
        return clasa;
    }

    public void setClasa(int clasa) {
        this.clasa = clasa;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public boolean getOcupat() { return ocupat; }
}
