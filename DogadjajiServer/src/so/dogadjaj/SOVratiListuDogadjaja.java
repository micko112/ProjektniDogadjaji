/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dogadjaj;

import so.gost.*;
import dbb.DBBroker;
import domain.Gost;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOVratiListuDogadjaja extends OpstaSistemskaOperacija {

    ArrayList<Gost> list;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Gost)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Gost!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        list = (ArrayList< Gost>) (ArrayList< ?>) DBBroker.getInstance().select(odo);
    }
    
    public ArrayList<Gost> getList() {
        return list;
    }
    
    public void setList(ArrayList<Gost> list) {
        this.list = list;
    }

}
