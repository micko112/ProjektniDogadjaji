/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import java.net.ServerSocket;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author user
 */
public class ThreadServer extends Thread {

    private ServerSocket serverSocket;
  
    private static final Set<String> aktivniKorisnici = new HashSet<>();

    public ThreadServer() {
        try {
            serverSocket = new ServerSocket(9000); 
            System.out.println("Server je pokrenut na portu 9000.");
        } catch (BindException ex) {
            System.err.println("Greska: Port 9000 se vec koristi. Server ne moze biti pokrenut.");
        } catch (IOException ex) {
            System.err.println("Greska pri pokretanju servera: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (serverSocket != null && !serverSocket.isClosed()) {
                System.out.println("Cekam na novog klijenta...");
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao sa adrese: " + socket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            }
        } catch (IOException ex) {
            System.out.println("Server socket je ugasen: " + ex.getMessage());
        }
    }
    
    public void stopServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server je zaustavljen.");
            }
        } catch (IOException ex) {
            System.err.println("Greska pri zaustavljanju servera: " + ex.getMessage());
        }
    }



    public static boolean dodajAktivnogKorisnika(String email) {
        if (aktivniKorisnici.contains(email)) {
            return false; 
        }
        aktivniKorisnici.add(email);
        return true;
    }

    public static void ukloniAktivnogKorisnika(String email) {
        aktivniKorisnici.remove(email);
    }
    
    public static Set<String> getAktivniKorisnici() {
        return aktivniKorisnici;
    }
}
