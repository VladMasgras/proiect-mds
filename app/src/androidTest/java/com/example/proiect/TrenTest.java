package com.example.proiect;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrenTest {

    @Test
    public void testTren(){
        Tren tren = new Tren(1234, 200, "Constanta", "18:00", "Bucuresti","20:00");
        assertEquals(1234, tren.getSerie());
        assertEquals(200, tren.getDistanta());
        assertEquals("Constanta", tren.getStatiePlecare());
        assertEquals("18:00", tren.getOraPlecare());
        assertEquals("Bucuresti", tren.getStatieSosire());
        assertEquals("20:00", tren.getOraSosire());
    }
}