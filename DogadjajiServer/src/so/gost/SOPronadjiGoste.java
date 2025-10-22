/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.gost;

import dbb.DBBroker;
import domain.Gost;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOPronadjiGoste extends OpstaSistemskaOperacija{
private ArrayList<Gost> lista;

    public ArrayList<Gost> getLista() {
        return lista;
    }
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
         if (!(odo instanceof Gost)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Gost!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> rezultat = DBBroker.getInstance().select((OpstiDomenskiObjekat) odo);
        lista = (ArrayList<Gost>) (ArrayList<?>) rezultat;
    }
    
}
