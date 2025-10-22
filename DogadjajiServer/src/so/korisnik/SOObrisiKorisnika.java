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

/**
 *
 * @author user
 */
public class SOObrisiKorisnika extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof Korisnik)){
            throw new Exception ("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().delete(odo);
    }
    
}
