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
public class Izvodjac extends OpstiDomenskiObjekat{
    int izvodjacId;
    String ime;
    Zanr zanr;

    public Izvodjac() {
    }

    public Izvodjac(int izvodjacId, String ime, Zanr zanr) {
        this.izvodjacId = izvodjacId;
        this.ime = ime;
        this.zanr = zanr;
    }
@Override
    public String nazivTabele() {
        return "izvodjac";
    }

    @Override
    public String alijas() {
        return "i";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
         ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Izvodjac i = new Izvodjac();
            i.setIzvodjacId(rs.getInt("i.izvodjacId"));
            i.setIme(rs.getString("i.ime"));
            i.setZanr(Zanr.valueOf(rs.getString("i.zanr")));
            lista.add(i);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ime, zanr)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "izvodjacId = " + izvodjacId;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + zanr + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ime = '" + ime + "', zanr = '" + zanr + "'";
    }

    @Override
    public String uslov() {
        return "";
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
        final Izvodjac other = (Izvodjac) obj;
        return this.izvodjacId == other.izvodjacId;
    }

    public int getIzvodjacId() {
        return izvodjacId;
    }

    public void setIzvodjacId(int izvodjacId) {
        this.izvodjacId = izvodjacId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    
    
    
}
