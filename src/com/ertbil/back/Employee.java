package com.ertbil.back;

public class Employee {
    private int id;
    private String ad;
    private String soyad;
    private String depart;
    private int maas;

    public Employee(int id, String ad, String soyad, String depart, int maas) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.depart = depart;
        this.maas = maas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public int getMaas() {
        return maas;
    }

    public void setMaas(int maas) {
        this.maas = maas;
    }
}
