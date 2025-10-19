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

/**
 *
 * @author user
 */
public class SOPrijaviKorisnika extends OpstaSistemskaOperacija{
    private Korisnik ulogovaniKorisnik;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof Korisnik)){
            throw  new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        Korisnik k = (Korisnik) odo;
        // dvostruki kast sa wildcardom
        ArrayList<Korisnik> korisnici = (ArrayList < Korisnik >) (ArrayList < ? >)DBBroker.getInstance().select(k);
        for (Korisnik kor : korisnici) {
            if(k.getEmail().equals(kor.getEmail()) && k.getLozinka().equals(kor.getLozinka())){
                ulogovaniKorisnik = kor;
                return;
            }
        }
        throw new Exception("Korisnicko ime i sifra nisu ispravni!");
    }

    public Korisnik getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }

    public void setUlogovaniKorisnik(Korisnik ulogovaniKorisnik) {
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }

   
}
