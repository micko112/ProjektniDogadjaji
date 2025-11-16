/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author user
 */
public class Nastup {
    
    private int LineupID;
    private int IzvodjacID;
    private int trajanje;
    private List<Pesma> pesme;

    public Nastup(int LineupID, int IzvodjacID, int trajanje) {
        this.LineupID = LineupID;
        this.IzvodjacID = IzvodjacID;
        this.trajanje = trajanje;
    }

    public Nastup() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    public int getIzvodjacID() {
        return IzvodjacID;
    }

    public int getLineupID() {
        return LineupID;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setIzvodjacID(int IzvodjacID) {
        this.IzvodjacID = IzvodjacID;
    }

    public void setLineupID(int LineupID) {
        this.LineupID = LineupID;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }
    
    
}
