/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbb;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import validator.PasswordHash;
/**
 *
 * @author user
 */
public class DBBroker {
    private static DBBroker instance;
    private Connection connection;
    
    private DBBroker() throws SQLException, IOException{
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfiguration.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Konekcija sa bazom podataka uspesno uspostavljena!");
            connection.setAutoCommit(false);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.err.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
            throw ex; 
        }
    }
     public static DBBroker getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
}
      // SELECT
    public ArrayList<OpstiDomenskiObjekat> select(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM " + odo.nazivTabele() + " " + odo.alijas()
                + " " + odo.join() + " " + odo.uslov();
        System.out.println("Executing query: " + upit); 
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        
        ArrayList<OpstiDomenskiObjekat> lista = odo.vratiListu(rs);
        return lista;
    }

    // INSERT 
    public PreparedStatement insert(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.nazivTabele() + " "
                + odo.koloneZaInsert() + " VALUES(" + odo.vrednostiZaInsert() + ")";
        System.out.println("Executing insert: " + upit); // Debugging
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
       
        return ps;
    }

    // UPDATE
    public void update(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "UPDATE " + odo.nazivTabele() + " SET "
                + odo.vrednostiZaUpdate() + " WHERE " + odo.vrednostZaPrimarniKljuc();
        System.out.println("Executing update: " + upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    // DELETE
    public void delete(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "DELETE FROM " + odo.nazivTabele() + " WHERE " + odo.vrednostZaPrimarniKljuc();
        System.out.println("Executing delete: " + upit); 
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

 public void commit() throws SQLException {
        try {
            connection.commit();
            System.out.println("Transakcija uspesno potvrdjena!");
        } catch (SQLException ex) {
            System.err.println("Transakcija nije potvrdjena!\n" + ex.getMessage());
            throw ex;
        }
    }

    public void rollback() throws SQLException {
        try {
            connection.rollback();
            System.err.println("Transakcija uspesno ponistena!");
        } catch (SQLException ex) {
            System.err.println("Greska pri ponistavanju transakcije!\n" + ex.getMessage());
            throw ex;
        }
    }

 // --- Dodatne metode iz tvog primera ---
    // (Integrisane su u generičke select/update metode, ali ostavljam ih kao reference
    // ako ti trebaju za specifične scenarije, mada je bolje koristiti generičke)

    // Ažuriranje lozinki na startu (ako su neheširane)
    private void updatePasswordsToHashed() throws SQLException {
        String query = "SELECT korisnikId, lozinka FROM korisnik";
        System.out.println(query);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("korisnikId");
            String plainPassword = rs.getString("lozinka");
            // Pretpostavka: heširana lozinka ima fiksnu dužinu, npr. 64 za SHA-256
            if (plainPassword.length() != 64) { 
                String hashedPassword = PasswordHash.hashPassword(plainPassword);

                String updateQuery = "UPDATE korisnik SET lozinka=? WHERE korisnikId=?";
                PreparedStatement ps = connection.prepareStatement(updateQuery);
                ps.setString(1, hashedPassword);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }
        commit(); // Potvrdi promene lozinki
        rs.close();
        stmt.close();
    }

    // Ažuriranje nove lozinke (ako je uneta)
    public void updateNewPasswordToHash(Korisnik korisnikChange) throws SQLException, Exception {
        // Prvo dohvatimo trenutnu lozinku iz baze da vidimo da li je već heširana
        Korisnik postojeciKorisnik = new Korisnik();
        postojeciKorisnik.setKorisnikId(korisnikChange.getKorisnikId());
        ArrayList<OpstiDomenskiObjekat> result = select(postojeciKorisnik);
        
        if (result.isEmpty()) {
            throw new Exception("Korisnik za izmenu ne postoji.");
        }
        
        postojeciKorisnik = (Korisnik) result.get(0);
        
        // Proveravamo da li je nova lozinka već heširana (dužina 64)
        // ili se razlikuje od postojeće heširane.
        // Ako je korisnik unio plain text, hashuj ga.
        // Ako je korisnik prosledio već heširanu lozinku (npr. iz nekog UI elementa koji ne prima lozinke),
        // i ako se razlikuje od postojeće, hashuj.
        // Ovo je tricky, jer ako GUI ne dobija lozinku nazad, onda ce uvek biti plain text.
        
        String novaLozinkaZaUpis = korisnikChange.getLozinka();
        
        // Pretpostavka da je uneta lozinka plain text, ako nije već heširana (dužina npr. 64)
        if (novaLozinkaZaUpis.length() != 64 || !novaLozinkaZaUpis.equals(postojeciKorisnik.getLozinka())) {
            novaLozinkaZaUpis = PasswordHash.hashPassword(novaLozinkaZaUpis);
        }
        
        // Postavi heširanu lozinku u objekat za update
        korisnikChange.setLozinka(novaLozinkaZaUpis);
        
        // Zatim pozovi generički update
        update(korisnikChange);
        commit();
    }
    
    public Connection getConnection() {
        return connection;
    }
}
