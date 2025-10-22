/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModels;

import controller.CController;
import domain.Gost;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TableModelGost extends AbstractTableModel {

    private ArrayList<Gost> listaGostiju;
    private final String[] kolone = {"ID", "Ime", "Prezime", "Telefon"};

    public TableModelGost() {
        try {
            this.listaGostiju = CController.getInstance().vratiSveGoste();
        } catch (Exception e) {
            System.err.println("Greska pri ucitavanju gostiju: " + e.getMessage());
            this.listaGostiju = new ArrayList<>();
        }
    }

    @Override
    public int getRowCount() {
        return listaGostiju.size();
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
        Gost g = listaGostiju.get(rowIndex);
        switch (columnIndex) {
            case 0: return g.getGostId();
            case 1: return g.getIme();
            case 2: return g.getPrezime();
            case 3: return g.getTelefon();
            default: return "N/A";
        }
    }
    
    public Gost getGost(int red) {
        return listaGostiju.get(red);
    }
    
    public void osveziTabelu() {
        try {
            listaGostiju = CController.getInstance().vratiSveGoste();
            fireTableDataChanged();
        } catch (Exception e) {
             System.err.println("Greska pri osvezavanju tabele gostiju: " + e.getMessage());
        }
    }
    public void setListaGostiju(ArrayList<Gost> listaGostiju) {
this.listaGostiju = listaGostiju;
fireTableDataChanged(); 
}
}
