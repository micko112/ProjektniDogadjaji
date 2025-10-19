/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Gost extends OpstiDomenskiObjekat{
    int gostId;
    String ime;
    String prezime;
    String telefon;

    public Gost() {
    }

    public Gost(int gostId, String ime, String prezime, String telefon) {
        this.gostId = gostId;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
    }



    @Override
    public int hashCode() {
        int hash = 4;
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
        final Gost other = (Gost) obj;
        return this.gostId == other.gostId;
    }

    @Override
    public String nazivTabele() {
       return "gost";
    }

    @Override
    public String alijas() {
        return "g";
    }

    @Override
    public String join() {
       return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
           ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Gost g = new Gost();
            g.setGostId(rs.getInt("g.gostId"));
            g.setIme(rs.getString("g.ime"));
            g.setPrezime(rs.getString("g.prezime"));
            g.setTelefon(rs.getString("g.telefon"));
            lista.add(g);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ime, prezime, telefon)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "gostid = " + gostId;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + telefon + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', telefon = '" + telefon + "'";
    }

    @Override
    public String uslov() {
//        if (prezime != null && !prezime.isEmpty()) {
//            return "WHERE prezime LIKE '" + prezime + "%'";
//        }
       return "";
    }

    @Override
    public String toString() {
        return "Gost{" + "gostId=" + gostId + ", ime=" + ime + ", prezime=" + prezime + ", telefon=" + telefon + '}';
    }

    public int getGostId() {
        return gostId;
    }

    public void setGostId(int gostId) {
        this.gostId = gostId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
}
