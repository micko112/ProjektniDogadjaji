/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validator;

import domain.Angazman;
import domain.Dogadjaj;
import domain.Gost;
import domain.Izvodjac;
import domain.Korisnik;
import domain.Potvrda;
import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public class Validator {

    // Regularni izrazi (Regex) za proveru formata
    private static final String NAME_REGEX = "^[a-zA-ZČčĆćŽžŠšĐđ ]{2,}$"; // Imena i prezimena, min 2 karaktera
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String PHONE_REGEX = "^\\+?[0-9\\s\\-/()]{6,}$"; // Telefon, min 6 cifara, dozvoljava razmake, crtice...
    private static final int MIN_PASSWORD_LENGTH = 8;
    
    
    // --- Korisnik validacija ---
    
    public static void validateKorisnik(Korisnik korisnik) throws Exception {
        if (korisnik == null) {
            throw new Exception("Korisnik ne sme biti null.");
        }
        if (korisnik.getIme() == null || !korisnik.getIme().matches(NAME_REGEX)) {
            throw new Exception("Ime korisnika nije u ispravnom formatu.");
        }
        if (korisnik.getPrezime() == null || !korisnik.getPrezime().matches(NAME_REGEX)) {
            throw new Exception("Prezime korisnika nije u ispravnom formatu.");
        }
        if (korisnik.getEmail() == null || !korisnik.getEmail().matches(EMAIL_REGEX)) {
            throw new Exception("Email korisnika nije u ispravnom formatu.");
        }
    }
    
    public static void validateKorisnikLogin(Korisnik korisnik) throws Exception {
        if (korisnik.getEmail() == null || korisnik.getEmail().trim().isEmpty()) {
            throw new Exception("Email za prijavu ne sme biti prazan.");
        }
        if (korisnik.getLozinka() == null || korisnik.getLozinka().isEmpty()) {
            throw new Exception("Lozinka za prijavu ne sme biti prazna.");
        }
    }
    
    // --- Gost validacija ---
    
    public static void validateGost(Gost gost) throws Exception {
        if (gost == null) {
            throw new Exception("Gost ne sme biti null.");
        }
        if (gost.getIme() == null || !gost.getIme().matches(NAME_REGEX)) {
            throw new Exception("Ime gosta nije u ispravnom formatu.");
        }
        if (gost.getPrezime() == null || !gost.getPrezime().matches(NAME_REGEX)) {
            throw new Exception("Prezime gosta nije u ispravnom formatu.");
        }
        if (gost.getTelefon() == null || !gost.getTelefon().matches(PHONE_REGEX)) {
            throw new Exception("Broj telefona gosta nije u ispravnom formatu.");
        }
    }

    // --- Dogadjaj validacija ---
    
    public static void validateDogadjaj(Dogadjaj dogadjaj) throws Exception {
        if (dogadjaj == null) {
            throw new Exception("Događaj ne sme biti null.");
        }
        if (dogadjaj.getNaziv() == null || dogadjaj.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv događaja ne sme biti prazan.");
        }
        if (dogadjaj.getDatumVreme() == null || dogadjaj.getDatumVreme().isBefore(LocalDateTime.now())) {
            throw new Exception("Datum i vreme događaja moraju biti u budućnosti.");
        }
        if (dogadjaj.getLokacija() == null || dogadjaj.getLokacija().getLokacijaId() <= 0) {
            throw new Exception("Događaj mora imati validnu lokaciju.");
        }
        if (dogadjaj.getKorisnik() == null || dogadjaj.getKorisnik().getKorisnikId() <= 0) {
            throw new Exception("Događaj mora biti povezan sa korisnikom (organizatorom).");
        }
        // Validacija za listu angažmana
        if (dogadjaj.getAngazmani()== null || dogadjaj.getAngazmani().isEmpty()) {
            throw new Exception("Događaj mora imati bar jedan angažman.");
        }
        for (Angazman a : dogadjaj.getAngazmani()) {
            validateAngazman(a); // Validira svaki pojedinačni angažman
        }
    }

    // --- Izvodjac validacija ---
    
    public static void validateIzvodjac(Izvodjac izvodjac) throws Exception {
        if (izvodjac == null) {
            throw new Exception("Izvođač ne sme biti null.");
        }
        if (izvodjac.getIme() == null || izvodjac.getIme().trim().isEmpty()) {
            throw new Exception("Ime izvođača ne sme biti prazno.");
        }
        if (izvodjac.getZanr() == null) {
            throw new Exception("Žanr izvođača mora biti definisan.");
        }
    }

    // --- Angazman validacija ---
    
    public static void validateAngazman(Angazman angazman) throws Exception {
        if (angazman == null) {
            throw new Exception("Angažman ne sme biti null.");
        }
        if (angazman.getIzvodjac() == null || angazman.getIzvodjac().getIzvodjacId() <= 0) {
            throw new Exception("Angažman mora imati validnog izvođača.");
        }
        if (angazman.getTrajanjeNastupa() <= 0) {
            throw new Exception("Trajanje nastupa mora biti pozitivan broj.");
        }
    }

    // --- Potvrda validacija ---
    
    public static void validatePotvrda(Potvrda potvrda) throws Exception {
        if (potvrda == null) {
            throw new Exception("Potvrda ne sme biti null.");
        }
        if (potvrda.getGost() == null || potvrda.getGost().getGostId() <= 0) {
            throw new Exception("Potvrda mora biti povezana sa validnim gostom.");
        }
        if (potvrda.getDogadjaj() == null || potvrda.getDogadjaj().getDogadjajId() <= 0) {
            throw new Exception("Potvrda mora biti povezana sa validnim događajem.");
        }
        if (potvrda.getDatumVreme() == null) {
            throw new Exception("Vreme potvrde mora biti zabeleženo.");
        }
    }
}
