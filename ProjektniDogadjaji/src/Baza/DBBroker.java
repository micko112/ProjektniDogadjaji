/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Baza;

import Controller.Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lokacija;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import model.Dogadjaj;
import model.Gost;
import model.Izvodjac;
import model.Lineup;
import model.Nastup;
import model.VrstaDogadjaja;
import model.ZanrMuzike;
import view.LokacijaTableModel;

/**
 *
 * @author user
 */
public class DBBroker {

    Konekcija connection = Konekcija.getInstance();

    public void dodajUBazu(Dogadjaj dog) {
        try {
            String upit = "INSERT INTO Dogadjaj (naziv, datum, vreme, vrsta) VALUES(?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

            ps.setString(0, dog.getNaziv());
            //ps.setString(1, dog.ge);
            //do ovde si stigao, treba prvo da uradis ostale klase pa na kraju dogadjaj 
            // mislim da treba i da napravis funkciju za brojanje gostiju 

            ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Dogadjaj> ucitajDogadjajeIzBaze() {
        List<Dogadjaj> dogadjaji = new ArrayList<>();

        try {
            String upit = "select * from dogadjaji";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("dogadjajID");
                String naziv = rs.getString("naziv");
                LocalDate datum = rs.getDate("datum").toLocalDate();
                LocalDateTime vreme = rs.getTime("vreme").toLocalTime().atDate(datum);
                VrstaDogadjaja vrsta = VrstaDogadjaja.valueOf(rs.getString("vrsta"));
                Dogadjaj dog = new Dogadjaj(id, naziv, datum, vreme, vrsta);
                dogadjaji.add(dog);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dogadjaji;
    }

    public List<Lokacija> ucitajLokacijeIzBaze() {

        List<Lokacija> lokacije = new ArrayList<>();

        try {
            String upit = "select * from lokacije";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("lokacijaID");
                String naziv = rs.getString("naziv");
                String adresa = rs.getString("adresa");
                int kapacitet = rs.getInt("kapacitet");
                Lokacija lok = new Lokacija(id, naziv, adresa, kapacitet);
                lokacije.add(lok);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lokacije;

    }

    public void dodajLokacijuUBazu(Lokacija lokacija) {

        try {
            String upit = "insert into lokacije(naziv,adresa,kapacitet) values (?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, lokacija.getNaziv());
            ps.setString(2, lokacija.getAdresa());
            ps.setInt(3, lokacija.getKapacitet());
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void izmeniLokacijuUBazi(Lokacija lokacijaZaIzmenu) {

        try {
            String upit = "update lokacije "
                    + "set naziv = ?, adresa =?, kapacitet = ? "
                    + "where lokacijaid =? ";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, lokacijaZaIzmenu.getNaziv());
            ps.setString(2, lokacijaZaIzmenu.getAdresa());
            ps.setInt(3, lokacijaZaIzmenu.getKapacitet());
            ps.setInt(4, lokacijaZaIzmenu.getLokacijaID());
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void obrisiLokacijuIzBaze(Lokacija lok) {
        try {
            String upit = "DELETE  FROM lokacije\n"
                    + "	 WHERE  lokacijaID=?\n"
                    + "	 ;";

            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, lok.getLokacijaID());
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Gost> UcitajGosteIzBaze() {
        List<Gost> gosti = new ArrayList<>();

        try {
            String upit = "select * from gosti";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("gostID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String telefon = rs.getString("telefon");
                Gost gost = new Gost(id, ime, prezime, telefon);
                gosti.add(gost);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gosti;

    }

    public void dodajGostaUBazu(Gost gost) {
        try {
            String upit = "INSERT INTO gosti(ime,Prezime,Telefon)\n"
                    + "VALUES(?,?,?); ";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, gost.getIme());
            ps.setString(2, gost.getPrezime());
            ps.setString(3, gost.getBrojTelefona());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void obrisiGostaIzBaze(Gost gost) {
        try {
            String upit = "DELETE FROM GOSTI\n"
                    + "WHERE gostID=?;";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, gost.getID());
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void IzmeniGostaUBazi(Gost gost) {
        try {
            String upit = "UPDATE gosti\n"
                    + "	SET ime =? ,prezime = ?, Telefon = ?"
                    + "	WHERE gostID=?;";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, gost.getIme());
            ps.setString(2, gost.getPrezime());
            ps.setString(3, gost.getBrojTelefona());
            ps.setInt(4, gost.getID());
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dodajIzvodjacaUBazu(Izvodjac izvodjac) {
        try {
            String upit = "INSERT INTO izvodjaci(ime,Zanr)\n"
                    + "VALUES(?,?); ";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, izvodjac.getIme());
            ps.setString(2, String.valueOf(izvodjac.getZanr()));

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Izvodjac> ucitajIzvodjaceIzBaze() {
        List<Izvodjac> izvodjaci = new ArrayList<>();

        try {
            String upit = "select * from izvodjaci";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("izvodjacID");
                String ime = rs.getString("ime");

                ZanrMuzike zanr = ZanrMuzike.valueOf(rs.getString("zanr"));
                Izvodjac izv = new Izvodjac(id, ime, zanr);
                izvodjaci.add(izv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return izvodjaci;
    }

    public int dodajLineupUBazu(Lineup lineup) {

        try {
            String upit = "Insert into lineup(vremeOd, vremeDo, dogadjajID)"
                    + "values (?,?,?)  ";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            ps.setTime(1, Time.valueOf(lineup.getVremeOd()));
            ps.setTime(2, Time.valueOf(lineup.getVremeDo()));
            ps.setInt(3, lineup.getDogadjajID());
            ps.executeUpdate();
            //
            Konekcija.getInstance().getConnection().commit();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Vraća generisani lineupID
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public List<Lineup> ucitajLineupIDizbaze() {
        List<Lineup> lista = new ArrayList<>();
        try {
            String upit = "select * from lineup";

            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int id = rs.getInt("lineupID");
                LocalTime vremeOd = rs.getTime("vremeOd").toLocalTime();
                LocalTime vremeDo = rs.getTime("vremeDo").toLocalTime();
                int dogID = rs.getInt("dogadjajID");
                Lineup lineup = new Lineup(id, vremeOd, vremeDo, dogID);
                lista.add(lineup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public Lineup ucitajPoslednjiLineup() {
        Lineup poslednjiLineup = null;
        try {
            String upit = "SELECT * FROM lineup ORDER BY lineupID DESC LIMIT 1";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            if (rs.next()) {
                int id = rs.getInt("lineupID");
                LocalTime vremeOd = rs.getTime("vremeOd").toLocalTime();
                LocalTime vremeDo = rs.getTime("vremeDo").toLocalTime();
                int dogID = rs.getInt("dogadjajID");
                poslednjiLineup = new Lineup(id, vremeOd, vremeDo, dogID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return poslednjiLineup; // Vraća poslednji lineup ili null ako nema rezultata
    }

    public void dodajNastupUBazu(Nastup nastup) {

        try {
            String upit = "INSERT INTO nastup2 (lineupID, izvodjacID, trajanje) VALUES (?, ?, ?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, nastup.getLineupID());
            ps.setInt(2, nastup.getIzvodjacID());
            ps.setInt(3, nastup.getTrajanje());
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Nastup> ucitajNastupeIzBaze() {
        List<Nastup> lista = new ArrayList<>();
        try {
            String upit = "select * from nastup2";

            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int IzvID = rs.getInt("izvodjacID");
                int LinID = rs.getInt("lineupID");
                int trajanje = rs.getInt("trajanje");
                Nastup nas = new Nastup(LinID, IzvID, trajanje);
                lista.add(nas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

}
