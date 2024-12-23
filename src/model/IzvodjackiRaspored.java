/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public class IzvodjackiRaspored {
    int ID;
    LocalDateTime vremeOd;
    LocalDateTime vremeDo;

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
        final IzvodjackiRaspored other = (IzvodjackiRaspored) obj;
        return this.ID == other.ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDateTime getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(LocalDateTime vremeOd) {
        this.vremeOd = vremeOd;
    }

    public LocalDateTime getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(LocalDateTime vremeDo) {
        this.vremeDo = vremeDo;
    }
    
    
}
