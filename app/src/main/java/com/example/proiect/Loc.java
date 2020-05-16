package com.example.proiect;

public class Loc {
    private String rezervare;
    private boolean luat;

    public Loc() {
        this.luat = false;
    }

    public Loc(String rezervare){
        this.rezervare = rezervare;
        luat = true;
    }

    public boolean isAvailable(){
        return !luat;
    }
}
