/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.izvodjac;


import so.lokacija.*;
import dbb.DBBroker;
import domain.Izvodjac;
import domain.Lokacija;

import domain.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOIzmeniIzvodjaca extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof Izvodjac)){
            throw new Exception ("Prosledjeni objekat nije instanca klase Izvodjac!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().update(odo);
    }
    
}
