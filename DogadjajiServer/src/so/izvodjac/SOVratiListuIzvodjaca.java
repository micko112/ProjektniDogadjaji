/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.izvodjac;

import dbb.DBBroker;
import domain.Izvodjac;

import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOVratiListuIzvodjaca extends OpstaSistemskaOperacija {

    ArrayList<Izvodjac> list;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Izvodjac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Izvodjac!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        list = (ArrayList< Izvodjac>) (ArrayList< ?>) DBBroker.getInstance().select(odo);
    }
    
    public ArrayList<Izvodjac> getList() {
        return list;
    }
    
    public void setList(ArrayList<Izvodjac> list) {
        this.list = list;
    }

}
