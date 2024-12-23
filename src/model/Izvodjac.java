/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Izvodjac {
    int ID;
    String ime;
    ZanrMuzike zanr;

    @Override
    public String toString() {
        return "izvodjac: "+ " " + ime + " " + zanr + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Izvodjac other = (Izvodjac) obj;
        return this.ID == other.ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public ZanrMuzike getZanr() {
        return zanr;
    }

    public void setZanr(ZanrMuzike zanr) {
        this.zanr = zanr;
    }
    
    
}
