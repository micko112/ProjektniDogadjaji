/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.potvrda;

import so.angazman.*;
import so.dogadjaj.*;
import so.gost.*;
import dbb.DBBroker;
import domain.Angazman;
import domain.Dogadjaj;
import domain.Gost;
import domain.OpstiDomenskiObjekat;
import domain.Potvrda;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOVratiListuPotvrda extends OpstaSistemskaOperacija {

    ArrayList<Potvrda> list;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Potvrda)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Potvrda!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        list = (ArrayList< Potvrda>) (ArrayList< ?>) DBBroker.getInstance().select(odo);
    }
    
    public ArrayList<Potvrda> getList() {
        return list;
    }
    
    public void setList(ArrayList<Potvrda> list) {
        this.list = list;
    }

}
