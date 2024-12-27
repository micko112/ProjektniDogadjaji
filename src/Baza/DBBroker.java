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
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.Dogadjaj;
import model.Gost;
import model.Lineup;
/**
 *
 * @author user
 */
public class DBBroker {
    
    Konekcija connection = Konekcija.getInstance();

    public List<Lokacija> ucitajLokacijeIzBaze() {
        
        List<Lokacija> lokacije = new ArrayList<>();
    
       try {
            String upit = "select * from lokacije";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("lokacijaID");
                String naziv = rs.getString("naziv");
                String adresa = rs.getString("adresa");
                int kapacitet = rs.getInt("kapacitet");
                Lokacija lok= new Lokacija(id, naziv, adresa, kapacitet);
                lokacije.add(lok);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return lokacije;
  
}

    public List<Gost> UcitajGosteIzBaze() {
          List<Gost> gosti = new ArrayList<>();
    
       try {
            String upit = "select * from gosti";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("gostID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                int telefon = rs.getInt("telefon");
                Gost gost= new Gost(id, ime, prezime, telefon);
                gosti.add(gost);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return gosti;
  
    }

    public List<Lineup> UcitajLineupIzBaze() {
         List<Lineup> lineup = new ArrayList<>();
    
       try {
            String upit = "select * from lineup";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("lineupID");
                LocalTime vremeOd = rs.getTime("vremeOd").toLocalTime();
                LocalTime vremeDo = rs.getTime("vremeDo").toLocalTime();
              
         
                Lineup l= new Lineup(id, vremeOd, vremeDo);
                lineup.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return lineup;
   }
  
    
    
   public void dodajUBazu(Dogadjaj dog) {
        try {
            String upit = "INSERT INTO Dogadjaj (naziv, datum, vreme, vrsta) VALUES(?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            
            ps.setString(0,  dog.getNaziv()); 
            //ps.setString(1, dog.ge);
            //do ovde si stigao, treba prvo da uradis ostale klase pa na kraju dogadjaj 
            // mislim da treba i da napravis funkciju za brojanje gostiju 
            
            ps.executeQuery();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void dodajLokacijuUBazu(Lokacija lokacija) {
        
        try {
            String upit = "insert into lokacije(naziv,adresa,kapacitet) values (?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, lokacija.getNaziv());
            ps.setString(2, lokacija.getAdresa());
            ps.setInt(3 , lokacija.getKapacitet());
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}