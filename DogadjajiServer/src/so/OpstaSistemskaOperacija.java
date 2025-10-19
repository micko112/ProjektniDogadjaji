/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import dbb.DBBroker;
import domain.OpstiDomenskiObjekat;

/**
 *
 * @author user
 */
public abstract class OpstaSistemskaOperacija {

    protected abstract void validate(OpstiDomenskiObjekat odo) throws Exception;
    protected abstract void execute(OpstiDomenskiObjekat odo) throws Exception;
    public final void templateExecute(OpstiDomenskiObjekat odo) throws Exception {
        try {
            validate(odo);
            execute(odo);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    // Apstraktne metode koje konkretne SO moraju da implementiraju
    
    
    private void commit() throws Exception {
        DBBroker.getInstance().getConnection().commit();
    }
    
    private void rollback() throws Exception {
        DBBroker.getInstance().getConnection().rollback();
    }
}
