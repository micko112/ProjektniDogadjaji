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
import so.gost.SOVratiListuGostiju;
import so.izvodjac.SOKreirajIzvodjaca;
import so.izvodjac.SOVratiListuIzvodjaca;
import so.korisnik.SOPrijaviKorisnika;
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
        String hash = PasswordHash.hashPassword(korisnik.getLozinka());
        korisnik.setLozinka(hash);
        so.templateExecute(korisnik);
        return so.getUlogovaniKorisnik();
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

    // --- Izvodjac operacije ---
    public void kreirajIzvodjaca(Izvodjac izvodjac) throws Exception {
        (new SOKreirajIzvodjaca()).templateExecute(izvodjac);
    }

    public ArrayList<Izvodjac> vratiListuIzvodjaca() throws Exception {
        SOVratiListuIzvodjaca so = new SOVratiListuIzvodjaca();
        so.templateExecute(new Izvodjac());
        return so.getList();
    }

    // --- Lokacija operacije ---
    public ArrayList<Lokacija> vratiListuLokacija() throws Exception {
        SOVratiListuLokacija so = new SOVratiListuLokacija();
        so.templateExecute(new Lokacija());
        return so.getList();
    }

    // --- Specifične operacije za liste (Angazman, Potvrda) ---
    public ArrayList<Angazman> vratiAngazmaneZaDogadjaj(Dogadjaj dogadjaj) throws Exception {
        SOVratiListuAngazmana so = new SOVratiListuAngazmana();

        Angazman angazman = new Angazman();
        angazman.setDogadjaj(dogadjaj);
        so.templateExecute(angazman);
        return so.getList();
    }
    
    public ArrayList<Potvrda> vratiPotvrdeZaDogadjaj(Dogadjaj dogadjaj) throws Exception {
        SOVratiListuPotvrda so = new SOVratiListuPotvrda();
        Potvrda potvrda = new Potvrda();
        potvrda.setDogadjaj(dogadjaj);
        so.templateExecute(potvrda);
        return so.getList();
    }
}
