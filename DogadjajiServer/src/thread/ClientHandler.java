/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.SController;
import domain.Korisnik;
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
            System.out.println("Klijent " + (ulogovaniKorisnik != null ? ulogovaniKorisnik.getEmail(): "") + " se diskonektovao.");
        } catch (Exception e) {
            System.out.println("Greska u ClientHandler-u: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Logout korisnika ako je bio ulogovan
            if (ulogovaniKorisnik != null) {
                ThreadServer.ukloniAktivnogKorisnika(ulogovaniKorisnik.getEmail());
                System.out.println("Korisnik " + ulogovaniKorisnik.getEmail()+ " je odjavljen.");
            }
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null,ResponseStatus.SUCCESS);
        try {
            Operation operation = request.getOperation();
            System.out.println("Primljena operacija: " + operation); // Za debagovanje

            switch (operation) {
                // --- Korisnik ---
                case LOGIN:
                    Korisnik zahtevani = (Korisnik) request.getArgument();
                    Korisnik ulogovani = SController.getInstance().login(zahtevani);
                    
                    if (!ThreadServer.dodajAktivnogKorisnika(ulogovani.getEmail())) {
                        throw new Exception("Korisnik je već ulogovan!");
                    }
                    
                    this.ulogovaniKorisnik = ulogovani;
                    response.setResult(ulogovani);
                    break;
                case LOGOUT:
                    Korisnik zaOdjavu = (Korisnik) request.getArgument();
                    ThreadServer.ukloniAktivnogKorisnika(zaOdjavu.getEmail());
                    this.ulogovaniKorisnik = null;
                    break;

                // --- Dogadjaj ---
                case KREIRAJ_DOGADJAJ:
                    SController.getInstance().kreirajDogadjaj((domain.Dogadjaj) request.getArgument());
                    break;
                case PROMENI_DOGADJAJ:
                    SController.getInstance().izmeniDogadjaj((domain.Dogadjaj) request.getArgument());
                    break;
                case OBRISI_DOGADJAJ:
                    SController.getInstance().obrisiDogadjaj((domain.Dogadjaj) request.getArgument());
                    break;
                case VRATI_SVE_DOGADJAJE:
                    response.setResult((SController.getInstance().vratiListuDogadjaja()));
                    break;
                case PRONADJI_DOGADJAJE:
                    response.setResult((SController.getInstance().pronadjiDogadjaje((domain.Dogadjaj) request.getArgument())));
                    break;

                // --- Gost ---
                case KREIRAJ_GOSTA:
                    SController.getInstance().kreirajGosta((domain.Gost) request.getArgument());
                    break;
                case PROMENI_GOSTA:
                    SController.getInstance().izmeniGosta((domain.Gost) request.getArgument());
                    break;
                case OBRISI_GOSTA:
                    SController.getInstance().obrisiGosta((domain.Gost) request.getArgument());
                    break;
                case VRATI_SVE_GOSTE:
                    response.setResult((SController.getInstance().vratiListuGostiju()));
                    break;
                
                // --- Izvodjac ---
                // ...ista logika kao za Gosta...
                
                // --- Lokacija ---
                case VRATI_SVE_LOKACIJE:
                    response.setResult((SController.getInstance().vratiListuLokacija()));
                    break;
                
                // --- Liste za Dogadjaj ---
                case VRATI_ANGAZMANE_ZA_DOGADJAJ:
                    response.setResult((SController.getInstance().vratiAngazmaneZaDogadjaj((domain.Dogadjaj) request.getArgument())));
                    break;
                case VRATI_POTVRDE_ZA_DOGADJAJ:
                    response.setResult(SController.getInstance().vratiPotvrdeZaDogadjaj((domain.Dogadjaj) request.getArgument()));
                    break;
                    
                default:
                    throw new Exception("Nepoznata operacija: " + operation);
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.ERROR);
            response.setException(e);
            System.err.println("Greska pri obradi operacije: " + request.getOperation() + "\n" + e.getMessage());
        }
        return response;
    }
}
