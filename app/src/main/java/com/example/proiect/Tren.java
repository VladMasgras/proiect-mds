package com.example.proiect;

public class Tren {
    private int serie,distanta;
    private String statiePlecare,oraPlecare,statieSosire,oraSosire;

    public Tren(){

    }

    public Tren(int serie, int distanta, String statiePlecare, String oraPlecare, String statieSosire, String oraSosire) {
        this.serie = serie;
        this.distanta = distanta;
        this.statiePlecare = statiePlecare;
        this.oraPlecare = oraPlecare;
        this.statieSosire = statieSosire;
        this.oraSosire = oraSosire;
    }


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
}
