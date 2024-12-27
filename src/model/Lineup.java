/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalTime;
import java.time.LocalTime;

/**
 *
 * @author user
 */
public class Lineup {
    int ID;
    LocalTime vremeOd;
    LocalTime vremeDo;

    public Lineup(int ID, LocalTime vremeOd, LocalTime vremeDo) {
        this.ID = ID;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
    }

    
    @Override
    public String toString() {
        return "IzvodjackiRaspored " + "vremeOd" + vremeOd + ", vremeDo" + vremeDo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Lineup other = (Lineup) obj;
        return this.ID == other.ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalTime getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(LocalTime vremeOd) {
        this.vremeOd = vremeOd;
    }

    public LocalTime getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(LocalTime vremeDo) {
        this.vremeDo = vremeDo;
    }
    
    
}
