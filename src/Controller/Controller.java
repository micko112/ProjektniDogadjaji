/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Baza.DBBroker;
import Baza.Konekcija;
import java.util.ArrayList;
import java.util.List;
import model.Dogadjaj;
import model.Gost;
import model.Izvodjac;
import model.Lineup;
import model.Lokacija;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Controller {
    DBBroker dbb = new DBBroker();
    List<Dogadjaj> listaDogadjaja = new ArrayList<>();
    List<Gost> listaGostiju = new ArrayList<>();
    List<Izvodjac> listaIzvodjaca = new ArrayList<>();
    List<Lineup> listaLineup = new ArrayList<>();
    List<Lokacija> listaLokacija = new ArrayList<>();

    private static Controller instance;
    
    public static  Controller getInstance(){
        if(instance==null){
            instance = new Controller();
            
        }
        return instance;
    }
    public Controller() {
        
        
    }

    public List<Lokacija> UcitajLokacijeIzBaze(){
      return dbb.ucitajLokacijeIzBaze();
        
    }
     public List<Gost> UcitajGosteIzBaze(){
      return dbb.UcitajGosteIzBaze();
        
    }
      public List<Lineup> UcitajLineupIzBaze(){
      return dbb.UcitajLineupIzBaze();
        
    }
      public void dodajUBazu(Dogadjaj dog) {
      dbb.dodajUBazu(dog);
    }
    
    public DBBroker getDbb() {
        return dbb;
    }

    public void setDbb(DBBroker dbb) {
        this.dbb = dbb;
    }

    public List<Dogadjaj> getListaDogadjaja() {
        return listaDogadjaja;
    }

    public void setListaDogadjaja(List<Dogadjaj> listaDogadjaja) {
        this.listaDogadjaja = listaDogadjaja;
    }

    public List<Gost> getListaGostiju() {
        return listaGostiju;
    }

    public void setListaGostiju(List<Gost> listaGostiju) {
        this.listaGostiju = listaGostiju;
    }

    public List<Izvodjac> getListaIzvodjaca() {
        return listaIzvodjaca;
    }

    public void setListaIzvodjaca(List<Izvodjac> listaIzvodjaca) {
        this.listaIzvodjaca = listaIzvodjaca;
    }

    public List<Lineup> getListaLineup() {
        return listaLineup;
    }

    public void setListaLineup(List<Lineup> listaLineup) {
        this.listaLineup = listaLineup;
    }

    public List<Lokacija> getListaLokacija() {
        return listaLokacija;
    }

    public void setListaLokacija(List<Lokacija> listaLokacija) {
        this.listaLokacija = listaLokacija;
    }

    public void dodajLokacijuUBazu(Lokacija lokacija) {
      dbb.dodajLokacijuUBazu(lokacija);
    }

  
    
            
}
