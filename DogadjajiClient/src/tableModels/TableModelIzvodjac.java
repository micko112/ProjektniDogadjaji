/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModels;
import controller.CController;
import domain.Izvodjac;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TableModelIzvodjac extends AbstractTableModel {

    private ArrayList<Izvodjac> listaIzvodjaca;
    private final String[] kolone = {"ID", "Ime", "Žanr"};

    public TableModelIzvodjac() {
        try {
            this.listaIzvodjaca = CController.getInstance().vratiSveIzvodjace();
        } catch (Exception e) {
            System.err.println("Greska pri ucitavanju izvodjaca: " + e.getMessage());
            this.listaIzvodjaca = new ArrayList<>();
        }
    }

    @Override
    public int getRowCount() {
        return listaIzvodjaca.size();
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
        Izvodjac i = listaIzvodjaca.get(rowIndex);
        switch (columnIndex) {
            case 0: return i.getIzvodjacId();
            case 1: return i.getIme();
            case 2: return i.getZanr(); 
            default: return "N/A";
        }
    }
    
    public Izvodjac getIzvodjac(int red) {
        return listaIzvodjaca.get(red);
    }
    
    public void osveziTabelu() {
        try {
            listaIzvodjaca = CController.getInstance().vratiSveIzvodjace();
            fireTableDataChanged();
        } catch (Exception e) {
             System.err.println("Greska pri osvezavanju tabele izvodjaca: " + e.getMessage());
        }
    }
}
