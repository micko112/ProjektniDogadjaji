/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dogadjaj;


import dbb.DBBroker;
import domain.Angazman;
import domain.Dogadjaj;

import domain.OpstiDomenskiObjekat;
import domain.Potvrda;
import java.time.LocalDateTime;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOIzmeniDogadjaj extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof Dogadjaj)){
            throw new Exception ("Prosledjeni objekat nije instanca klase Dogadjaj!");
        }
        Dogadjaj d = (Dogadjaj) odo;
        if(d.getAngazmani().isEmpty()){
            throw new Exception("Dogadjaj nema angazmane!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
         Dogadjaj dogadjaj = (Dogadjaj) odo;

    DBBroker.getInstance().update(dogadjaj);
    DBBroker.getInstance().obrisiSveAngazmaneZaDogadjaj(dogadjaj);
    DBBroker.getInstance().obrisiSvePotvrdeZaDogadjaj(dogadjaj);

    if (dogadjaj.getAngazmani() != null) {
        for (Angazman angazman : dogadjaj.getAngazmani()) {
            angazman.setDogadjaj(dogadjaj);
            DBBroker.getInstance().insert(angazman);
        }
    }
    if (dogadjaj.getPotvrde() != null) {
        for (Potvrda potvrda : dogadjaj.getPotvrde()) {
            potvrda.setDogadjaj(dogadjaj);
            potvrda.setDatumVreme(LocalDateTime.now()); 
            DBBroker.getInstance().insert(potvrda);
        }
    }
//        DBBroker.getInstance().update(odo);
//          Dogadjaj d = (Dogadjaj) odo;
//        DBBroker.getInstance().delete(d.getAngazmani().get(0));
//
//        for (Angazman a : d.getAngazmani()) {
//            DBBroker.getInstance().insert(a);
//        }
    }
    
}
