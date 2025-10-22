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
public class Korisnik extends OpstiDomenskiObjekat {
    int korisnikId;
    String ime;
    String prezime;
    String email;
    String lozinka;

    public Korisnik() {
    }

    public Korisnik(int korisnikId, String ime, String prezime, String email, String lozinka) {
        this.korisnikId = korisnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
    }

    @Override
    public String nazivTabele() {
        return "korisnik";
    }

    @Override
    public String alijas() {
        return "k";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Korisnik k = new Korisnik(rs.getInt("k.korisnikId"),
                    rs.getString("k.ime"),
                    rs.getString("k.prezime"),
                    rs.getString("k.email"),
                    rs.getString("k.lozinka"));
            lista.add(k);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(ime, prezime, email, lozinka)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " korisnikId = " + korisnikId;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', '" + lozinka + "'";

    }

    @Override
    public String vrednostiZaUpdate() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', email = '" + email + "', lozinka = '" + lozinka + "'";
    }

    @Override
    public String uslov() {
        if (email != null && !email.isEmpty()) {
        return "WHERE email = '" + email + "'";
    }
        return "";
    }

    @Override
    public String toString() {
        return "Korisnik{" + "korisnikId=" + korisnikId + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", lozinka=" + lozinka + '}';
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
        final Korisnik other = (Korisnik) obj;
        if (this.korisnikId != other.korisnikId) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.lozinka, other.lozinka);
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

}
