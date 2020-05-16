package com.example.proiect;

import java.util.ArrayList;
import java.util.List;

public class Vagon {
    private int clasa,nrLocuri;
    private List<Loc> locuri;

    public Vagon(int clasa, int nrLocuri){
        this.clasa = clasa;
        this.nrLocuri = nrLocuri;
        locuri = new ArrayList<Loc>(nrLocuri);
    }

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
}
