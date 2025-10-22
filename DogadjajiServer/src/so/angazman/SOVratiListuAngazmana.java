/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.angazman;

import so.dogadjaj.*;
import so.gost.*;
import dbb.DBBroker;
import domain.Angazman;
import domain.Dogadjaj;
import domain.Gost;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOVratiListuAngazmana extends OpstaSistemskaOperacija {

    ArrayList<Angazman> list;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Angazman)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Angazman!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        list = (ArrayList< Angazman>) (ArrayList< ?>) DBBroker.getInstance().select(odo);
    }
    
    public ArrayList<Angazman> getList() {
        return list;
    }
    
    public void setList(ArrayList<Angazman> list) {
        this.list = list;
    }

}
