/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import enums.VrstaDogadjaja;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.sql.Timestamp;

/**
 *
 * @author user
 */
public class Dogadjaj extends OpstiDomenskiObjekat{
    private int dogadjajId;
    String naziv;
    LocalDateTime datumVreme;
    VrstaDogadjaja vrstaDogadjaja;
    Lokacija lokacija;
    Korisnik korisnik;
    ArrayList<Angazman> angazmani;
    
    public Dogadjaj() {
    }
    public Dogadjaj(int dogadjajId, String naziv, LocalDateTime datumVreme, VrstaDogadjaja vrstaDogadjaja, Lokacija lokacija, Korisnik korisnik) {
        this.dogadjajId = dogadjajId;
        this.naziv = naziv;
        this.datumVreme = datumVreme;
        this.vrstaDogadjaja = vrstaDogadjaja;
        this.lokacija = lokacija;
        this.korisnik = korisnik;
       
    }
    public Dogadjaj( String naziv, LocalDateTime datumVreme, VrstaDogadjaja vrstaDogadjaja, Lokacija lokacija, Korisnik korisnik, ArrayList<Angazman> agazmani) {
        this.naziv = naziv;
        this.datumVreme = datumVreme;
        this.vrstaDogadjaja = vrstaDogadjaja;
        this.lokacija = lokacija;
        this.korisnik = korisnik;
        this.angazmani = agazmani;
    }
        public Dogadjaj( String naziv, LocalDateTime datumVreme, VrstaDogadjaja vrstaDogadjaja, Lokacija lokacija, Korisnik korisnik) {
        this.naziv = naziv;
        this.datumVreme = datumVreme;
        this.vrstaDogadjaja = vrstaDogadjaja;
        this.lokacija = lokacija;
        this.korisnik = korisnik;
    }
    

    
    
    @Override
    public String nazivTabele() {
        return "dogadjaj";
    }

    @Override
    public String alijas() {
        return "d";
    }

    @Override
    public String join() {
                return " JOIN lokacija l ON (d.lokacijaid = l.lokacijaid)"
                    + " JOIN korisnik k ON (d.korisnikid = k.korisnikid)";
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

            Korisnik k = new Korisnik();
            k.setKorisnikId(rs.getInt("k.korisnikId"));
            k.setIme(rs.getString("k.ime"));
            k.setPrezime(rs.getString("k.prezime"));
            k.setEmail(rs.getString("k.email"));
            k.setLozinka(rs.getString("k.lozinka"));
            
            Dogadjaj d = new Dogadjaj();
            d.setDogadjajId(rs.getInt("d.dogadjajId"));
            d.setNaziv(rs.getString("d.naziv"));
            d.setDatumVreme(rs.getTimestamp("d.datumVreme").toLocalDateTime());
            d.setVrstaDogadjaja(VrstaDogadjaja.valueOf(rs.getString("d.vrstaDogadjaja")));
            d.setLokacija(l);
            d.setKorisnik(k);

            lista.add(d);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(naziv, datumVreme, vrstaDogadjaja, lokacijaId, korisnikId)";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "dogadjajId = " + dogadjajId;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + Timestamp.valueOf(datumVreme) + "', '" + vrstaDogadjaja.name() + "', " 
               + lokacija.getLokacijaId()+ ", " + korisnik.getKorisnikId();
    
    }

    @Override
    public String vrednostiZaUpdate() {
                return "naziv = '" + naziv + "', datumVreme = '" + Timestamp.valueOf(datumVreme) + "', vrstaDogadjaja = '" + vrstaDogadjaja.name() 
               + "', lokacijaId = " + lokacija.getLokacijaId()+ ", korisnikId = " + korisnik.getKorisnikId();
    }

    @Override
    public String uslov() {
        return "";
    }

    public int getDogadjajId() {
        return dogadjajId;
    }

    public void setDogadjajId(int dogadjajId) {
        this.dogadjajId = dogadjajId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public VrstaDogadjaja getVrstaDogadjaja() {
        return vrstaDogadjaja;
    }

    public void setVrstaDogadjaja(VrstaDogadjaja vrstaDogadjaja) {
        this.vrstaDogadjaja = vrstaDogadjaja;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public ArrayList<Angazman> getAngazmani() {
        return angazmani;
    }

    public void setAngazmani(ArrayList<Angazman> angazmani) {
        this.angazmani = angazmani;
    }

    @Override
    public String toString() {
        return "Dogadjaj{" + "dogadjajId=" + dogadjajId + ", naziv=" + naziv + ", datumVreme=" + datumVreme + ", vrstaDogadjaja=" + vrstaDogadjaja + ", lokacija=" + lokacija + ", korisnik=" + korisnik + ", angazmani=" + angazmani + '}';
    }

  

   

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public int hashCode() {
        int hash = 8;
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
        final Dogadjaj other = (Dogadjaj) obj;
        if (this.dogadjajId != other.dogadjajId) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.datumVreme, other.datumVreme)) {
            return false;
        }
        if (this.vrstaDogadjaja != other.vrstaDogadjaja) {
            return false;
        }
        if (!Objects.equals(this.lokacija, other.lokacija)) {
            return false;
        }
        if (!Objects.equals(this.korisnik, other.korisnik)) {
            return false;
        }
        return Objects.equals(this.angazmani, other.angazmani);
    }

    


    
    
}
