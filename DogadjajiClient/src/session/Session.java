/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session;

import domain.Korisnik;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author user
 */
public class Session {
    private static Session instance;
    Socket socket;
    Korisnik ulogovaniKorisnik;

    private Session() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
            throw new RuntimeException("Server nije dostupan. Pokrenite server i pokusajte ponovo.");
        }
    }
        public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Korisnik getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }

    public void setUlogovaniKorisnik(Korisnik ulogovaniKorisnik) {
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }
        
}
