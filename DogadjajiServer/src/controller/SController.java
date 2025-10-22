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
import so.angazman.SOVratiListuAngazmana;
import so.dogadjaj.SOIzmeniDogadjaj;
import so.dogadjaj.SOKreirajDogadjaj;
import so.dogadjaj.SOObrisiDogadjaj;
import so.dogadjaj.SOVratiListuDogadjaja;
import so.gost.SOIzmeniGosta;
import so.gost.SOKreirajGosta;
import so.gost.SOObrisiGosta;
import so.gost.SOPronadjiGoste;
import so.gost.SOVratiListuGostiju;
import so.izvodjac.SOIzmeniIzvodjaca;
import so.izvodjac.SOKreirajIzvodjaca;
import so.izvodjac.SOObrisiIzvodjaca;
import so.izvodjac.SOVratiListuIzvodjaca;
import so.korisnik.SOIzmeniKorisnika;
import so.korisnik.SOKreirajKorisnika;
import so.korisnik.SOObrisiKorisnika;
import so.korisnik.SOPrijaviKorisnika;
import so.korisnik.SOVratiListuKorisnika;
import so.lokacija.SOIzmeniLokaciju;
import so.lokacija.SOKreirajLokaciju;
import so.lokacija.SOObrisiLokaciju;
import so.lokacija.SOVratiListuLokacija;
import so.potvrda.SOVratiListuPotvrda;
import validator.PasswordHash;

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

        SOPrijaviKorisnika so = new SOPrijaviKorisnika();
//        String hash = PasswordHash.hashPassword(korisnik.getLozinka());
//        korisnik.setLozinka(hash);
        so.templateExecute(korisnik);
        return so.getUlogovaniKorisnik();
    }
    public void kreirajKorisnika(Korisnik korisnik) throws Exception {
        (new SOKreirajKorisnika()).templateExecute(korisnik);
    }
    
    public void izmeniKorisnika(Korisnik korisnik) throws Exception {
        (new SOIzmeniKorisnika()).templateExecute(korisnik);
    }
    
    public void obrisiKorisnika(Korisnik korisnik) throws Exception {
        (new SOObrisiKorisnika()).templateExecute(korisnik);
    }
    public ArrayList<Korisnik> vratiListuKorisnika() throws Exception {
        SOVratiListuKorisnika so = new SOVratiListuKorisnika();
        so.templateExecute(new Korisnik()); 
        return so.getList();
    }

    // --- Dogadjaj operacije ---
    public void kreirajDogadjaj(Dogadjaj dogadjaj) throws Exception {
        (new SOKreirajDogadjaj()).templateExecute(dogadjaj);
    }
    
    public void izmeniDogadjaj(Dogadjaj dogadjaj) throws Exception {
        (new SOIzmeniDogadjaj()).templateExecute(dogadjaj);
    }
    
    public void obrisiDogadjaj(Dogadjaj dogadjaj) throws Exception {
        (new SOObrisiDogadjaj()).templateExecute(dogadjaj);
    }

    public ArrayList<Dogadjaj> vratiListuDogadjaja() throws Exception {
        SOVratiListuDogadjaja so = new SOVratiListuDogadjaja();
        so.templateExecute(new Dogadjaj()); 
        return so.getList();
    }
    
    // --- Gost operacije ---
    public void kreirajGosta(Gost gost) throws Exception {
        (new SOKreirajGosta()).templateExecute(gost);
    }
    
    public void izmeniGosta(Gost gost) throws Exception {
        (new SOIzmeniGosta()).templateExecute(gost);
    }

    public void obrisiGosta(Gost gost) throws Exception {
        (new SOObrisiGosta()).templateExecute(gost);
    }
    
    public ArrayList<Gost> vratiListuGostiju() throws Exception {
        SOVratiListuGostiju so = new SOVratiListuGostiju();
        so.templateExecute(new Gost());
        return so.getList();
    }
    public ArrayList<Gost> pronadjiGoste(Gost kriterijum) throws Exception {
    SOPronadjiGoste so = new SOPronadjiGoste();
    so.templateExecute(kriterijum);
    return so.getLista();
}

    // --- Izvodjac operacije ---
    public void kreirajIzvodjaca(Izvodjac izvodjac) throws Exception {
        (new SOKreirajIzvodjaca()).templateExecute(izvodjac);
    }
    public void izmeniIzvodjaca(Izvodjac izvodjac) throws Exception {
        (new SOIzmeniIzvodjaca()).templateExecute(izvodjac);
    }

    public void obrisiIzvodjaca(Izvodjac izvodjac) throws Exception {
        (new SOObrisiIzvodjaca()).templateExecute(izvodjac);
    }
    public ArrayList<Izvodjac> vratiListuIzvodjaca() throws Exception {
        SOVratiListuIzvodjaca so = new SOVratiListuIzvodjaca();
        so.templateExecute(new Izvodjac());
        return so.getList();
    }

    // --- Lokacija operacije ---
        public void kreirajLokaciju(Lokacija lokacija) throws Exception {
        (new SOKreirajLokaciju()).templateExecute(lokacija);
    }
    public void izmeniLokaciju(Lokacija lokacija) throws Exception {
        (new SOIzmeniLokaciju()).templateExecute(lokacija);
    }

    public void obrisiLokaciju(Lokacija lokacija) throws Exception {
        (new SOObrisiLokaciju()).templateExecute(lokacija);
    }
    public ArrayList<Lokacija> vratiListuLokacija() throws Exception {
        SOVratiListuLokacija so = new SOVratiListuLokacija();
        so.templateExecute(new Lokacija());
        return so.getList();
    }
    

    // --- Specifične operacije za liste (Angazman, Potvrda) ---
    public ArrayList<Angazman> vratiAngazmaneZaDogadjaj(Dogadjaj dogadjaj) throws Exception {
        SOVratiListuAngazmana so = new SOVratiListuAngazmana();

        Angazman kriterijum = new Angazman();
        kriterijum.setDogadjaj(dogadjaj);
        so.templateExecute(kriterijum);   
        return so.getList();
    }
    
    public ArrayList<Potvrda> vratiPotvrdeZaDogadjaj(Dogadjaj dogadjaj) throws Exception {
        SOVratiListuPotvrda so = new SOVratiListuPotvrda();
        Potvrda kriterijum = new Potvrda();
        kriterijum.setDogadjaj(dogadjaj);
        so.templateExecute(kriterijum);
        return so.getList();
    }
}
