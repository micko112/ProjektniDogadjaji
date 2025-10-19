/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import enums.Zanr;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Angazman extends OpstiDomenskiObjekat{
    Dogadjaj dogadjaj;
    Izvodjac izvodjac;
    int trajanjeNastupa;

    public Angazman() {
    }

    public Angazman(Dogadjaj dogadjaj, Izvodjac izvodjac, int trajanjeNastupa) {
        this.dogadjaj = dogadjaj;
        this.izvodjac = izvodjac;
        this.trajanjeNastupa = trajanjeNastupa;
    }

    @Override
    public String nazivTabele() {
        return "angazman";
    }

    @Override
    public String alijas() {
       return "a";
    }

    @Override
    public String join() {
        return " JOIN izvodjac i ON (a.izvodjacId = i.izvodjacId)";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
       ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Izvodjac i = new Izvodjac();
            i.setIzvodjacId(rs.getInt("i.izvodjacId"));
            i.setIme(rs.getString("i.ime"));
            i.setZanr(Zanr.valueOf(rs.getString("i.zanr")));
            
            Angazman a = new Angazman();
            a.setIzvodjac(i);
            a.setTrajanjeNastupa(rs.getInt("a.trajanjeNastupa"));
            // Dogadjaj objekat se mora postaviti spolja, jer se ova lista traži za jedan specifičan događaj
            
            lista.add(a);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(dogadjaj, izvodjac, trajanjeNastupa)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "dogadjajId = " + dogadjaj.getDogadjajId()+ " AND izvodjacId = " + izvodjac.getIzvodjacId();
    }

    @Override
    public String vrednostiZaInsert() {
        return dogadjaj.getDogadjajId()+ ", " + izvodjac.getIzvodjacId()+ ", " + trajanjeNastupa;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "trajanjeNastupa = " + trajanjeNastupa;
    }

    @Override
    public String uslov() {
//        if (dogadjaj != null) {
//            return "WHERE a.dogadjajid = " + dogadjaj.getDogadjajid();
//        }
        return "";
    }

    @Override
    public String toString() {
        return "Aganzman{" + "dogadjaj=" + dogadjaj + ", izvodjac=" + izvodjac + ", trajanjeNastupa=" + trajanjeNastupa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Angazman other = (Angazman) obj;
        if (this.trajanjeNastupa != other.trajanjeNastupa) {
            return false;
        }
        if (!Objects.equals(this.dogadjaj, other.dogadjaj)) {
            return false;
        }
        return Objects.equals(this.izvodjac, other.izvodjac);
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }

    public void setDogadjaj(Dogadjaj dogadjaj) {
        this.dogadjaj = dogadjaj;
    }

    public Izvodjac getIzvodjac() {
        return izvodjac;
    }

    public void setIzvodjac(Izvodjac izvodjac) {
        this.izvodjac = izvodjac;
    }

    public int getTrajanjeNastupa() {
        return trajanjeNastupa;
    }

    public void setTrajanjeNastupa(int trajanjeNastupa) {
        this.trajanjeNastupa = trajanjeNastupa;
    }
    
}
