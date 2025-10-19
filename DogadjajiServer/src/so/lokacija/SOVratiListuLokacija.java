/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.lokacija;

import so.gost.*;
import dbb.DBBroker;
import domain.Gost;
import domain.Lokacija;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOVratiListuLokacija extends OpstaSistemskaOperacija {

    ArrayList<Lokacija> list;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Lokacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lokacija!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        list = (ArrayList< Lokacija>) (ArrayList< ?>) DBBroker.getInstance().select(odo);
    }
    
    public ArrayList<Lokacija> getList() {
        return list;
    }
    
    public void setList(ArrayList<Lokacija> list) {
        this.list = list;
    }

}
