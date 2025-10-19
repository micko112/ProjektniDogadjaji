/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.sql.Timestamp;
/**
 *
 * @author user
 */
public class Potvrda extends OpstiDomenskiObjekat{
    Dogadjaj dogadjaj;
    Gost gost;
    boolean status;
    LocalDateTime datumVreme;
    
    @Override
    public String nazivTabele() {
        return "potvrda";
    }

    @Override
    public String alijas() {
        return "p";
    }

    @Override
    public String join() {
        return " JOIN gost g ON (p.gostId = g.gostId)";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
         ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            Gost g = new Gost();
            g.setGostId(rs.getInt("g.gostId"));
            g.setIme(rs.getString("g.ime"));
            g.setPrezime(rs.getString("g.prezime"));
            g.setTelefon(rs.getString("g.telefon"));
            
            Potvrda p = new Potvrda();
            p.setGost(g);
            p.setStatus(rs.getBoolean("p.status"));
            p.setDatumVreme(rs.getTimestamp("p.DatumVreme").toLocalDateTime());
            // Dogadjaj se i ovde postavlja spolja
            
            lista.add(p);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(gostId, dogadjajId, status, datumVreme)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "gostId = " + gost.getGostId() + " AND dogadjajId = " + dogadjaj.getDogadjajId();
    }

    @Override
    public String vrednostiZaInsert() {
        return gost.getGostId() + ", " + dogadjaj.getDogadjajId() + ", " + status + ", '" + Timestamp.valueOf(datumVreme) + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "status = " + status + ", datumVreme = '" + Timestamp.valueOf(datumVreme) + "'";
    }

    @Override
    public String uslov() {
        if (dogadjaj != null && dogadjaj.getDogadjajId() > 0) {
            return "WHERE p.dogadjajId = " + dogadjaj.getDogadjajId();
        }
        return "";
    }

    @Override
    public String toString() {
        return "Potvrda{" + "dogadjaj=" + dogadjaj + ", gost=" + gost + ", status=" + status + ", datumVreme=" + datumVreme + '}';
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
        final Potvrda other = (Potvrda) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.dogadjaj, other.dogadjaj)) {
            return false;
        }
        if (!Objects.equals(this.gost, other.gost)) {
            return false;
        }
        return Objects.equals(this.datumVreme, other.datumVreme);
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }

    public void setDogadjaj(Dogadjaj dogadjaj) {
        this.dogadjaj = dogadjaj;
    }

    public Gost getGost() {
        return gost;
    }

    public void setGost(Gost gost) {
        this.gost = gost;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }
    
}
