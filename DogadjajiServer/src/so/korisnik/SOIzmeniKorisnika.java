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

/**
 *
 * @author user
 */
public class SOIzmeniKorisnika extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof Korisnik)){
            throw new Exception ("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        Korisnik korisnikZaIzmenu = (Korisnik) odo;
        
        // Proveravamo da li je korisnik uneo novu lozinku
        if (korisnikZaIzmenu.getLozinka() != null && !korisnikZaIzmenu.getLozinka().isEmpty()) {
            // Ako jeste, heširaj je
            String hesiranaLozinka = PasswordHash.hashPassword(korisnikZaIzmenu.getLozinka());
            korisnikZaIzmenu.setLozinka(hesiranaLozinka);
        } else {
           
        }
        
        DBBroker.getInstance().update(korisnikZaIzmenu);
    }
    
}
