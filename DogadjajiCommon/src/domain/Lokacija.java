/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Lokacija extends OpstiDomenskiObjekat{
    int lokacijaId;
    String naziv;
    String adresa;
    int kapacitet;

    public Lokacija() {
    }

    public Lokacija(int lokacijaId, String naziv, String adresa, int kapacitet) {
        this.lokacijaId = lokacijaId;
        this.naziv = naziv;
        this.adresa = adresa;
        this.kapacitet = kapacitet;
    }

    @Override
    public String nazivTabele() {
        return "lokacija";
    }

    @Override
    public String alijas() {
        return "l";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Lokacija l = new Lokacija();
            l.setLokacijaId(rs.getInt("l.lokacijaId"));
            l.setNaziv(rs.getString("l.naziv"));
            l.setAdresa(rs.getString("l.adresa"));
            l.setKapacitet(rs.getInt("l.kapacitet"));
            lista.add(l);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(naziv, adresa, kapacitet)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "lokacijaId = " + lokacijaId;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + adresa + "', " + kapacitet;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "naziv = '" + naziv + "', adresa = '" + adresa + "', kapacitet = " + kapacitet;
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String toString() {
        return naziv;
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
        final Lokacija other = (Lokacija) obj;
        if (this.lokacijaId != other.lokacijaId) {
            return false;
        }
        if (this.kapacitet != other.kapacitet) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.adresa, other.adresa);
    }
    
    public int getLokacijaId() {
        return lokacijaId;
    }

    public void setLokacijaId(int lokacijaId) {
        this.lokacijaId = lokacijaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

   
}
