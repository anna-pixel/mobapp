package com.example.wochentagsrechner;

import java.io.Serializable;

public class DBList implements Serializable {

    private int id, datum;
    private String eingabe, ergebnis, kommentar;


    public DBList(int id, int datum, String eingabe, String ergebnis, String kommentar) {
        this.id = id;
        this.datum = datum;
        this.eingabe = eingabe;
        this.ergebnis = ergebnis;
        this.kommentar = kommentar;
    }

    public DBList(){

    }

    @Override
    public  String toString(){
        return  eingabe + ":" + ergebnis;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDatum() {
        return datum;
    }

    public void setDatum(int datum) {
        this.datum = datum;
    }

    public String getEingabe() {
        return eingabe;
    }

    public void setEingabe(String eingabe) {
        this.eingabe = eingabe;
    }

    public String getErgebnis() {
        return ergebnis;
    }

    public void setErgebnis(String ergebnis) {
        this.ergebnis = ergebnis;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
