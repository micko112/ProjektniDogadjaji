/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Angazman;
import domain.Dogadjaj;
import domain.Gost;
import domain.Izvodjac;
import domain.Korisnik;
import domain.Lokacija;
import domain.Potvrda;
import java.util.ArrayList;
import so.gost.SOIzmeniGosta;
import so.gost.SOKreirajGosta;
import so.gost.SOObrisiGosta;
import so.gost.SOVratiListuGostiju;
import so.izvodjac.SOKreirajIzvodjaca;
import so.izvodjac.SOVratiListuIzvodjaca;
import so.korisnik.SOPrijaviKorisnika;
import so.lokacija.SOVratiListuLokacija;

/**
 *
 * @author user
 */
public class SController {
    private static SController instance;

    private SController() {}

    public static SController getInstance() {
        if (instance == null) {
            instance = new SController();
        }
        return instance;
    }

    // --- Korisnik operacije ---
    public Korisnik login(Korisnik korisnik) throws Exception {
        // SOLogin će biti specijalna operacija koja ne nasleđuje OpstaSO
        // jer ima drugačiji tok (provera heša, itd.)
        SOPrijaviKorisnika so = new SOPrijaviKorisnika();
        so.execute(korisnik);
        return so.getUlogovaniKorisnik();
    }

    // --- Dogadjaj operacije ---
    public void kreirajDogadjaj(Dogadjaj dogadjaj) throws Exception {
        // Kreiranje događaja je transakcija, pa će SO biti složenija
        (new SOKreirajDogadjaj()).templateExecute(dogadjaj);
    }
    
    public void promeniDogadjaj(Dogadjaj dogadjaj) throws Exception {
        (new SOPromeniDogadjaj()).templateExecute(dogadjaj);
    }
    
    public void obrisiDogadjaj(Dogadjaj dogadjaj) throws Exception {
        (new SOObrisiDogadjaj()).templateExecute(dogadjaj);
    }

    public ArrayList<Dogadjaj> vratiSveDogadjaje() throws Exception {
        SOVratiSveDogadjaje so = new SOVratiSveDogadjaje();
        so.templateExecute(new Dogadjaj()); // Šaljemo prazan objekat kao filter
        return so.getLista();
    }
    
    public ArrayList<Dogadjaj> pronadjiDogadjaje(Dogadjaj kriterijum) throws Exception {
        SOPronadjiDogadjaje so = new SOPronadjiDogadjaje();
        so.templateExecute(kriterijum);
        return so.getLista();
    }

    // --- Gost operacije ---
    public void kreirajGosta(Gost gost) throws Exception {
        (new SOKreirajGosta()).templateExecute(gost);
    }
    
    public void promeniGosta(Gost gost) throws Exception {
        (new SOIzmeniGosta()).templateExecute(gost);
    }

    public void obrisiGosta(Gost gost) throws Exception {
        (new SOObrisiGosta()).templateExecute(gost);
    }
    
    public ArrayList<Gost> vratiSveGoste() throws Exception {
        SOVratiListuGostiju so = new SOVratiListuGostiju();
        so.templateExecute(new Gost());
        return so.getList();
    }

    // --- Izvodjac operacije ---
    public void kreirajIzvodjaca(Izvodjac izvodjac) throws Exception {
        (new SOKreirajIzvodjaca()).templateExecute(izvodjac);
    }

    public ArrayList<Izvodjac> vratiSveIzvodjace() throws Exception {
        SOVratiListuIzvodjaca so = new SOVratiListuIzvodjaca();
        so.templateExecute(new Izvodjac());
        return so.getList();
    }

    // --- Lokacija operacije ---
    public ArrayList<Lokacija> vratiSveLokacije() throws Exception {
        SOVratiListuLokacija so = new SOVratiListuLokacija();
        so.templateExecute(new Lokacija());
        return so.getList();
    }

    // --- Specifične operacije za liste (Angazman, Potvrda) ---
    public ArrayList<Angazman> vratiAngazmaneZaDogadjaj(Dogadjaj dogadjaj) throws Exception {
        SOVratiAngazmaneZaDogadjaj so = new SOVratiAngazmaneZaDogadjaj();
        // Pravimo šablon za pretragu
        Angazman kriterijum = new Angazman();
        kriterijum.setDogadjaj(dogadjaj);
        so.templateExecute(kriterijum);
        return so.getLista();
    }
    
    public ArrayList<Potvrda> vratiPotvrdeZaDogadjaj(Dogadjaj dogadjaj) throws Exception {
        SOVratiPotvrdeZaDogadjaj so = new SOVratiPotvrdeZaDogadjaj();
        Potvrda kriterijum = new Potvrda();
        kriterijum.setDogadjaj(dogadjaj);
        so.templateExecute(kriterijum);
        return so.getLista();
    }
}
