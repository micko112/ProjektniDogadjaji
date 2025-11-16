/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Baza;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class Konekcija {
   private static Konekcija instance;
    private  Connection connection;

    public Konekcija() {
          
       try {
           String url = "jdbc:mysql://localhost:3306/projektnidogadjaji";
           connection = DriverManager.getConnection(url, "root", "");
           connection.setAutoCommit(false);
       } catch (SQLException ex) {
           Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    public static Konekcija getInstance() {
        if(instance == null){
            instance = new Konekcija();
        }
        return instance;
    }

    public static void setInstance(Konekcija instance) {
        Konekcija.instance = instance;
    }

    public Connection getConnection() {
        return connection;
    }
public void disconnect() throws SQLException{
    if(connection!=null || !connection.isClosed()){
        connection.close();
    }
}
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
    
    
  
    
}
