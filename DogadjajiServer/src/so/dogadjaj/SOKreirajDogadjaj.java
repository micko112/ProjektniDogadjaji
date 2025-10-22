/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dogadjaj;

import so.gost.*;
import dbb.DBBroker;
import domain.Angazman;
import domain.Dogadjaj;
import domain.Gost;
import domain.OpstiDomenskiObjekat;
import domain.Potvrda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.OpstaSistemskaOperacija;
import validator.Validator;

/**
 *
 * @author user
 */
public class SOKreirajDogadjaj extends OpstaSistemskaOperacija {

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Dogadjaj)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Dogadjaj!");
        }
        // Koristimo naš centralizovani Validator
        Dogadjaj dogadjaj = (Dogadjaj) odo;
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        try {

            Dogadjaj dogadjaj = (Dogadjaj) odo;
            PreparedStatement ps = DBBroker.getInstance().insert(odo);
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int dogId = keys.getInt(1);
            System.out.println("Kljuc dogadjaja je: " + dogId);
            dogadjaj.setDogadjajId(dogId);
            
            for (Angazman angazman : dogadjaj.getAngazmani()) {
                angazman.setDogadjaj(dogadjaj);
                DBBroker.getInstance().insert(angazman);
            }
            
            if (dogadjaj.getPotvrde() != null && !dogadjaj.getPotvrde().isEmpty()) {
            System.out.println("Pronadjeno " + dogadjaj.getPotvrde().size() + " potvrda za upis."); // Debugging poruka
            for (Potvrda potvrda : dogadjaj.getPotvrde()) {
                potvrda.setDogadjaj(dogadjaj); 
                DBBroker.getInstance().insert(potvrda);
            }
        }
        } catch (Exception e) {
            System.err.println("GRESKA U INSERT Otpremnica: " + e.getMessage());
            throw e;
        }
    }
}
