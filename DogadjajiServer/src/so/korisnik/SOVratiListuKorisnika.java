/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.korisnik;

import so.gost.*;
import dbb.DBBroker;
import domain.Gost;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author user
 */
public class SOVratiListuKorisnika extends OpstaSistemskaOperacija {

    ArrayList<Korisnik> list;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        list = (ArrayList< Korisnik>) (ArrayList< ?>) DBBroker.getInstance().select(odo);
    }
    
    public ArrayList<Korisnik> getList() {
        return list;
    }
    
    public void setList(ArrayList<Korisnik> list) {
        this.list = list;
    }

}
