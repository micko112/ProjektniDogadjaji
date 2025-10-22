/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.korisnik;

import so.gost.*;
import dbb.DBBroker;
import domain.Gost;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;
import validator.PasswordHash;
import validator.Validator;

/**
 *
 * @author user
 */
public class SOKreirajKorisnika extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof Korisnik)){
            throw new Exception ("Prosledjeni objekat nije instanca klase Korisnik!");
        }
        Validator.validateKorisnik((Korisnik) odo);
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        Korisnik korisnik = (Korisnik) odo;
        String cistaLozinka = korisnik.getLozinka();
        String hesiranaLozinka = PasswordHash.hashPassword(cistaLozinka);
        korisnik.setLozinka(hesiranaLozinka);
        DBBroker.getInstance().insert(korisnik);
    }




}
