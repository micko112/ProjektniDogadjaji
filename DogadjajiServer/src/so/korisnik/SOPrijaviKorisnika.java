/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.korisnik;

import dbb.DBBroker;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;
import validator.PasswordHash;
import validator.Validator;

/**
 *
 * @author user
 */
public class SOPrijaviKorisnika extends OpstaSistemskaOperacija {

    private Korisnik ulogovaniKorisnik;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
        Validator.validateKorisnikLogin((Korisnik) odo);
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        Korisnik korisnikZaPrijavu = (Korisnik) odo;
        // dvostruki kast sa wildcardom
        Korisnik kriterijum = new Korisnik();
        kriterijum.setEmail(korisnikZaPrijavu.getEmail());
        ArrayList<Korisnik> korisnici = (ArrayList< Korisnik>) (ArrayList< ?>) DBBroker.getInstance().select(kriterijum);
//        for (Korisnik kor : korisnici) {
//            if(k.getEmail().equals(kor.getEmail()) && k.getLozinka().equals(kor.getLozinka())){
//                ulogovaniKorisnik = kor;
//                return;
//            }
//        }
        if (korisnici.isEmpty()) {
            throw new Exception("Korisnik sa datim email-om ne postoji!");
        }
        Korisnik pronadjeniKorisnikIzBaze = (Korisnik) korisnici.get(0);
        String unetaLozinka = korisnikZaPrijavu.getLozinka();
        String hashIzBaze = pronadjeniKorisnikIzBaze.getLozinka();
        String hashUneteLozinke = PasswordHash.hashPassword(unetaLozinka);
        if (hashUneteLozinke.equals(hashIzBaze)) {
            ulogovaniKorisnik = pronadjeniKorisnikIzBaze;
        } else {
            throw new Exception("Pogrešna lozinka!");
        }
        if (korisnici.isEmpty()) {
            throw new Exception("Korisnik sa datim email-om ne postoji!");
        }

    }

    public Korisnik getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }

    public void setUlogovaniKorisnik(Korisnik ulogovaniKorisnik) {
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }

}
