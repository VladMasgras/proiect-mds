package com.example.proiect;

public class Loc {
    private String rezervare;
    private boolean luat;
    private int nrLoc;

    public Loc() {}

    public Loc(int nrLoc) {
        this.luat = false;
        this.nrLoc = nrLoc;
    }

    public Loc(String rezervare){
        this.rezervare = rezervare;
        luat = true;
    }

    public boolean isAvailable(){
        return !luat;
    }

    public int getNrLoc(){
        return nrLoc;
    }

    @Override
    public String toString(){
        return "" + nrLoc;
    }
}
