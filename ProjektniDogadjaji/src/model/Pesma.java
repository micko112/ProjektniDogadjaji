/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Pesma {
    private int id;
    private String naziv;
    private Izvodjac izvodjac;
    private int trajanje;  // Trajanje u sekundama

    public Pesma(int id, String naziv, Izvodjac izvodjac, int trajanje) {
        this.id = id;
        this.naziv = naziv;
        this.izvodjac = izvodjac;
        this.trajanje = trajanje;
    }

    // Getteri i setteri
    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public Izvodjac getIzvodjac() {
        return izvodjac;
    }

    public int getTrajanje() {
        return trajanje;
    }
}
