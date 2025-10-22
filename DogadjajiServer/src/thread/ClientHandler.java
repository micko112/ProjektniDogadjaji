/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.SController;
import domain.Angazman;
import domain.Dogadjaj;
import domain.Gost;
import domain.Izvodjac;
import domain.Korisnik;
import domain.Lokacija;
import domain.Potvrda;
import java.net.Socket;
import enums.Operation;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import transfer.Request;
import transfer.Response;
import transfer.ResponseStatus;

/**
 *
 * @author user
 */
public class ClientHandler extends Thread {

    private final Socket socket;
    private Korisnik ulogovaniKorisnik;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (SocketException e) {
            System.out.println("Klijent " + (ulogovaniKorisnik != null ? ulogovaniKorisnik.getEmail() : "") + " se diskonektovao.");
        } catch (Exception e) {
            System.out.println("Greska u ClientHandler-u: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Logout korisnika ako je bio ulogovan
            if (ulogovaniKorisnik != null) {
                ThreadServer.ukloniAktivnogKorisnika(ulogovaniKorisnik.getEmail());
                System.out.println("Korisnik " + ulogovaniKorisnik.getEmail() + " je odjavljen.");
            }
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.SUCCESS);
        try {
            Operation operation = request.getOperation();
            System.out.println("Primljena operacija: " + operation); // Za debagovanje

            switch (operation) {
                case LOGIN -> {
                    Korisnik zahtevani = (Korisnik) request.getArgument();
                    Korisnik ulogovani = SController.getInstance().login(zahtevani);

                    if (!ThreadServer.dodajAktivnogKorisnika(ulogovani.getEmail())) {
                        throw new Exception("Korisnik je već ulogovan!");
                    }

                    this.ulogovaniKorisnik = ulogovani;
                    response.setResult(ulogovani);
                }
                case LOGOUT -> {
                    Korisnik zaOdjavu = (Korisnik) request.getArgument();
                    ThreadServer.ukloniAktivnogKorisnika(zaOdjavu.getEmail());
                    this.ulogovaniKorisnik = null;
                }
                case KREIRAJ_KORISNIKA ->
                    SController.getInstance().kreirajKorisnika((Korisnik) request.getArgument());

                case IZMENI_KORISNIKA ->
                    SController.getInstance().izmeniKorisnika((Korisnik) request.getArgument());

                case OBRISI_KORISNIKA ->
                    SController.getInstance().obrisiKorisnika((Korisnik) request.getArgument());

                case KREIRAJ_DOGADJAJ ->
                    SController.getInstance().kreirajDogadjaj((Dogadjaj) request.getArgument());

                case VRATI_SVE_KORISNIKE ->
                    response.setResult((SController.getInstance().vratiListuKorisnika()));
                    
                case IZMENI_DOGADJAJ ->
                    SController.getInstance().izmeniDogadjaj((Dogadjaj) request.getArgument());

                case OBRISI_DOGADJAJ ->
                    SController.getInstance().obrisiDogadjaj((Dogadjaj) request.getArgument());

                case VRATI_SVE_DOGADJAJE ->
                    response.setResult((SController.getInstance().vratiListuDogadjaja()));

                case KREIRAJ_GOSTA ->
                    SController.getInstance().kreirajGosta((Gost) request.getArgument());

                case IZMENI_GOSTA ->
                    SController.getInstance().izmeniGosta((Gost) request.getArgument());

                case OBRISI_GOSTA ->
                    SController.getInstance().obrisiGosta((Gost) request.getArgument());

                case VRATI_SVE_GOSTE ->
                    response.setResult((SController.getInstance().vratiListuGostiju()));
                case PRONADJI_GOSTE ->
                        response.setResult(SController.getInstance().pronadjiGoste((Gost) request.getArgument()));

                case KREIRAJ_IZVODJACA ->
                    SController.getInstance().kreirajIzvodjaca((Izvodjac) request.getArgument());

                case IZMENI_IZVODJACA ->
                    SController.getInstance().izmeniIzvodjaca((Izvodjac) request.getArgument());

                case OBRISI_IZVODJACA ->
                    SController.getInstance().obrisiIzvodjaca((Izvodjac) request.getArgument());

                case VRATI_SVE_IZVODJACE ->
                    response.setResult((SController.getInstance().vratiListuIzvodjaca()));

                case KREIRAJ_LOKACIJU ->
                    SController.getInstance().kreirajLokaciju((Lokacija) request.getArgument());

                case IZMENI_LOKACIJU ->
                    SController.getInstance().izmeniLokaciju((Lokacija) request.getArgument());

                case OBRISI_LOKACIJU ->
                    SController.getInstance().obrisiLokaciju((Lokacija) request.getArgument());

                case VRATI_SVE_LOKACIJE ->
                    response.setResult((SController.getInstance().vratiListuLokacija()));

                case VRATI_ANGAZMANE_ZA_DOGADJAJ -> {
                    // 1. Primi filter objekat
                    Angazman angazmanFilter = (Angazman) request.getArgument();
                    // 2. Izvuci Dogadjaj iz njega
                    Dogadjaj dogadjajZaPretragu = angazmanFilter.getDogadjaj();
                    // 3. Prosledi Dogadjaj u SController
                    response.setResult(SController.getInstance().vratiAngazmaneZaDogadjaj(dogadjajZaPretragu));
                    //response.setResult((SController.getInstance().vratiAngazmaneZaDogadjaj((Dogadjaj) request.getArgument())));
                }
                case VRATI_POTVRDE_ZA_DOGADJAJ -> {
                    Potvrda potvrdaFilter = (Potvrda) request.getArgument();
                    Dogadjaj dogadjajZaPretragu = potvrdaFilter.getDogadjaj();
                    response.setResult(SController.getInstance().vratiPotvrdeZaDogadjaj(dogadjajZaPretragu));
                }
                default ->
                    throw new Exception("Nepoznata operacija: " + operation);
            }
            // --- Korisnik ---
            // --- Dogadjaj ---
            // --- Gost ---
            // --- Lokacija ---
            // --- Liste za Dogadjaj ---
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.ERROR);
            response.setException(e);
            System.err.println("Greska pri obradi operacije: " + request.getOperation() + "\n" + e.getMessage());
        }
        return response;
    }
}
