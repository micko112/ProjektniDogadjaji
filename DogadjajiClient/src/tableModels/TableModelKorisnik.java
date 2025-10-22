/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModels;

import controller.CController;
import domain.Korisnik;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TableModelKorisnik extends AbstractTableModel {

    private ArrayList<Korisnik> listaKorisnika;
    private final String[] kolone = {"ID", "Ime", "Prezime", "Email"};

    public TableModelKorisnik() {
        try {
            this.listaKorisnika = CController.getInstance().vratiSveKorisnike(); 
        } catch (Exception e) {
            System.err.println("Greska pri ucitavanju korisnika: " + e.getMessage());
            this.listaKorisnika = new ArrayList<>();
        }
    }

    @Override
    public int getRowCount() { return listaKorisnika.size(); }
    @Override
    public int getColumnCount() { return kolone.length; }
    @Override
    public String getColumnName(int column) { return kolone[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = listaKorisnika.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getKorisnikId();
            case 1: return k.getIme();
            case 2: return k.getPrezime();
            case 3: return k.getEmail();
            default: return "N/A";
        }
    }
    
    public Korisnik getKorisnik(int red) {
        return listaKorisnika.get(red);
    }
    
    public void osveziTabelu() {
        try {
            listaKorisnika = CController.getInstance().vratiSveKorisnike();
            fireTableDataChanged();
        } catch (Exception e) { e.printStackTrace(); }
    }
}