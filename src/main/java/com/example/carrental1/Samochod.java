package com.example.carrental1;

public class Samochod {
    private String tablice;
    private String marka;
    private String model;
    private String paliwo;
    private int miejsca;
    private int cena;
    private int ubezpieczenie24;
    private int dostepnosc;

    public Samochod(String tablice, String marka, String model, String paliwo, int miejsca, int cena, int ubezpieczenie24 ,int dostepnosc) {
        this.tablice = tablice;
        this.marka = marka;
        this.model = model;
        this.paliwo = paliwo;
        this.miejsca = miejsca;
        this.cena = cena;
        this.ubezpieczenie24 = ubezpieczenie24;
        this.dostepnosc = dostepnosc;
    }

    // Gettery i Settery
    public String getTablice() {
        return tablice;
    }

    public void setTablice(String tablice) {
        this.tablice = tablice;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPaliwo() {
        return paliwo;
    }

    public void setPaliwo(String kolor) {
        this.paliwo = paliwo;
    }

    public int getMiejsca() {
        return miejsca;
    }

    public void setMiejsca(int miejsca) {
        this.miejsca = miejsca;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getUbezpieczenie24() {
        return ubezpieczenie24;
    }

    public void setUbezpieczenie24(int cena) {
        this.ubezpieczenie24 = ubezpieczenie24;
    }

    public int getDostepnosc() {
        return dostepnosc;
    }

    public void setDostepnosc(int dostepnosc) {
        this.dostepnosc = dostepnosc;
    }

    @Override
    public String toString() {
        return "Samochod{" +
                "tablice='" + tablice + '\'' +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", paliwo='" + paliwo + '\'' +
                ", miejsca=" + miejsca +
                ", cena=" + cena +
                ", ubezpieczenie24=" + ubezpieczenie24 +
                ", dostepnosc=" + dostepnosc +
                '}';
    }
}
