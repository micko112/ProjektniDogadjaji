/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModels;

import controller.CController;
import domain.Lokacija;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TableModelLokacija extends AbstractTableModel {

    private ArrayList<Lokacija> listaLokacija;
    private final String[] kolone = {"ID", "Naziv", "Adresa", "Kapacitet"};

    public TableModelLokacija() {
        try {
            this.listaLokacija = CController.getInstance().vratiSveLokacije();
        } catch (Exception e) {
            System.err.println("Greska pri ucitavanju lokacija: " + e.getMessage());
            this.listaLokacija = new ArrayList<>(); // Prazna lista u slučaju greške
        }
    }


    @Override
    public int getRowCount() {
        return listaLokacija.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lokacija l = listaLokacija.get(rowIndex);
        switch (columnIndex) {
            case 0: return l.getLokacijaId();
            case 1: return l.getNaziv();
            case 2: return l.getAdresa();
            case 3: return l.getKapacitet();
            default: return "N/A";
        }
    }
    

    public Lokacija getLokacija(int red) {
        return listaLokacija.get(red);
    }
    

    public void osveziTabelu() {
        try {
            listaLokacija = CController.getInstance().vratiSveLokacije();
            fireTableDataChanged(); 
        } catch (Exception e) {
             System.err.println("Greska pri osvezavanju tabele lokacija: " + e.getMessage());
        }
    }
}
