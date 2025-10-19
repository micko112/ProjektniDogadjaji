/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author user
 */
public enum Operation {
    LOGIN,
    LOGOUT,

    // --- Dogadjaj  ---
    KREIRAJ_DOGADJAJ,
    PROMENI_DOGADJAJ,
    OBRISI_DOGADJAJ,
    VRATI_SVE_DOGADJAJE,
    PRONADJI_DOGADJAJE, // Pretraga po kriterijumu

    // --- Gost  ---
    KREIRAJ_GOSTA,
    PROMENI_GOSTA,
    OBRISI_GOSTA,
    VRATI_SVE_GOSTE,
    PRONADJI_GOSTE,

    // --- Izvodjac  ---
    KREIRAJ_IZVODJACA,
    PROMENI_IZVODJACA,
    OBRISI_IZVODJACA,
    VRATI_SVE_IZVODJACE,
    PRONADJI_IZVODJACE,

    // --- Lokacija  ---
    KREIRAJ_LOKACIJU,
    PROMENI_LOKACIJU,
    OBRISI_LOKACIJU,
    VRATI_SVE_LOKACIJE,
    PRONADJI_LOKACIJE,

    // --- Angazman operacije ---
    // Angažmani se obično ne kreiraju sami, već kao deo događaja,
    // ali ako ti trebaju posebne operacije, evo ih:
    KREIRAJ_ANGAZMAN,
    OBRISI_ANGAZMAN,
    VRATI_ANGAZMANE_ZA_DOGADJAJ,

    // --- Potvrda operacije ---
    // Ista logika kao za angažmane
    KREIRAJ_POTVRDU,
    PROMENI_POTVRDU, // Npr. gost menja status iz "dolazi" u "ne dolazi"
    VRATI_POTVRDE_ZA_DOGADJAJ
}
