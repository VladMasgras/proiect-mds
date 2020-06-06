package com.example.proiect;

public class Bilet {

    private int serie,distanta, vagon, loc;
    private String statiePlecare,oraPlecare,statieSosire,oraSosire;

    Bilet(){}

    public Bilet(int serie, int distanta, int vagon, int loc, String statiePlecare, String oraPlecare, String statieSosire, String oraSosire) {
        this.serie = serie;
        this.distanta = distanta;
        this.vagon = vagon;
        this.loc = loc;
        this.statiePlecare = statiePlecare;
        this.oraPlecare = oraPlecare;
        this.statieSosire = statieSosire;
        this.oraSosire = oraSosire;
    }

    public int getSerie() {
        return serie;
    }

    public int getDistanta() {
        return distanta;
    }

    public int getVagon() {
        return vagon;
    }

    public int getLoc() {
        return loc;
    }

    public String getStatiePlecare() {
        return statiePlecare;
    }

    public String getOraPlecare() {
        return oraPlecare;
    }

    public String getStatieSosire() {
        return statieSosire;
    }

    public String getOraSosire() {
        return oraSosire;
    }
}
