/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dogadjaj;

import so.gost.*;
import dbb.DBBroker;
import domain.Dogadjaj;
import domain.Gost;
import domain.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOObrisiDogadjaj extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof Dogadjaj)){
            throw new Exception ("Prosledjeni objekat nije instanca klase Gost!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        Dogadjaj dogadjajZaBrisanje = (Dogadjaj) odo;
        DBBroker.getInstance().obrisiSvePotvrdeZaDogadjaj(dogadjajZaBrisanje);
    

    DBBroker.getInstance().obrisiSveAngazmaneZaDogadjaj(dogadjajZaBrisanje);
        DBBroker.getInstance().delete(odo);
    }
    
}
