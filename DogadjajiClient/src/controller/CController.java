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
import enums.Operation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.ResponseStatus;

/**
 *
 * @author user
 */
public class CController {

    private static CController instance;

    public static CController getInstance() throws Exception {
        if (instance == null) {
            instance = new CController();
        }
        return instance;
    }

    private CController() throws IOException {

    }

    private Object sendRequest(Operation operation, Object argument) throws Exception {
        Request request = new Request(operation, argument);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.ERROR)) {
            throw response.getException();
        } else {
            return response.getResult();
        }
    }
     // --- Korisnik  ---
    public Korisnik login(Korisnik korisnik) throws Exception {
        return (Korisnik) sendRequest(Operation.LOGIN, korisnik);
    }

    public void logout(Korisnik korisnik) throws Exception {
        sendRequest(Operation.LOGOUT, korisnik);
    }

    // --- Dogadjaj  ---
    public void kreirajDogadjaj(Dogadjaj dogadjaj) throws Exception {
        sendRequest(Operation.KREIRAJ_DOGADJAJ, dogadjaj);
    }

    public void promeniDogadjaj(Dogadjaj dogadjaj) throws Exception {
        sendRequest(Operation.PROMENI_DOGADJAJ, dogadjaj);
    }

    public void obrisiDogadjaj(Dogadjaj dogadjaj) throws Exception {
        sendRequest(Operation.OBRISI_DOGADJAJ, dogadjaj);
    }
    
    public ArrayList<Dogadjaj> vratiSveDogadjaje() throws Exception {
        return (ArrayList<Dogadjaj>) sendRequest(Operation.VRATI_SVE_DOGADJAJE, null);
    }

    public ArrayList<Dogadjaj> pronadjiDogadjaje(Dogadjaj kriterijum) throws Exception {
        return (ArrayList<Dogadjaj>) sendRequest(Operation.PRONADJI_DOGADJAJE, kriterijum);
    }

    // --- Gost  ---
    public void kreirajGosta(Gost gost) throws Exception {
        sendRequest(Operation.KREIRAJ_GOSTA, gost);
    }
    
    public void promeniGosta(Gost gost) throws Exception {
        sendRequest(Operation.PROMENI_GOSTA, gost);
    }
    
    public void obrisiGosta(Gost gost) throws Exception {
        sendRequest(Operation.OBRISI_GOSTA, gost);
    }

    public ArrayList<Gost> vratiSveGoste() throws Exception {
        return (ArrayList<Gost>) sendRequest(Operation.VRATI_SVE_GOSTE, null);
    }

    public ArrayList<Gost> pronadjiGoste(Gost kriterijum) throws Exception {
        return (ArrayList<Gost>) sendRequest(Operation.PRONADJI_GOSTE, kriterijum);
    }

    // --- Izvodjac  ---
    public void kreirajIzvodjaca(Izvodjac izvodjac) throws Exception {
        sendRequest(Operation.KREIRAJ_IZVODJACA, izvodjac);
    }

    public void promeniIzvodjaca(Izvodjac izvodjac) throws Exception {
        sendRequest(Operation.PROMENI_IZVODJACA, izvodjac);
    }

    public void obrisiIzvodjaca(Izvodjac izvodjac) throws Exception {
        sendRequest(Operation.OBRISI_IZVODJACA, izvodjac);
    }

    public ArrayList<Izvodjac> vratiSveIzvodjace() throws Exception {
        return (ArrayList<Izvodjac>) sendRequest(Operation.VRATI_SVE_IZVODJACE, null);
    }

    public ArrayList<Izvodjac> pronadjiIzvodjace(Izvodjac kriterijum) throws Exception {
        return (ArrayList<Izvodjac>) sendRequest(Operation.PRONADJI_IZVODJACE, kriterijum);
    }

    // --- Lokacija  ---
    public void kreirajLokaciju(Lokacija lokacija) throws Exception {
        sendRequest(Operation.KREIRAJ_LOKACIJU, lokacija);
    }
    
    public void promeniLokaciju(Lokacija lokacija) throws Exception {
        sendRequest(Operation.PROMENI_LOKACIJU, lokacija);
    }
    
    public void obrisiLokaciju(Lokacija lokacija) throws Exception {
        sendRequest(Operation.OBRISI_LOKACIJU, lokacija);
    }
    
    public ArrayList<Lokacija> vratiSveLokacije() throws Exception {
        return (ArrayList<Lokacija>) sendRequest(Operation.VRATI_SVE_LOKACIJE, null);
    }
    
    public ArrayList<Lokacija> pronadjiLokacije(Lokacija kriterijum) throws Exception {
        return (ArrayList<Lokacija>) sendRequest(Operation.PRONADJI_LOKACIJE, kriterijum);
    }
    
    // --- Angazman i Potvrda operacije ---
    // Ove operacije su specifične jer obično vraćaju liste vezane za jedan događaj.
    public ArrayList<Angazman> vratiAngazmaneZaDogadjaj(Dogadjaj dogadjaj) throws Exception {
        return (ArrayList<Angazman>) sendRequest(Operation.VRATI_ANGAZMANE_ZA_DOGADJAJ, dogadjaj);
    }
    
    public ArrayList<Potvrda> vratiPotvrdeZaDogadjaj(Dogadjaj dogadjaj) throws Exception {
        return (ArrayList<Potvrda>) sendRequest(Operation.VRATI_POTVRDE_ZA_DOGADJAJ, dogadjaj);
    }


}
