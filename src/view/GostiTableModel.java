/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Gost;

/**
 *
 * @author user
 */
public class GostiTableModel extends AbstractTableModel{
    List<Gost> gosti = new ArrayList<>();
    String kolone[] = {"gostID", "Ime","Prezime","broj Telefona"};
    public GostiTableModel( List<Gost> gosti){
        this.gosti = gosti;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

   
    
    @Override
    public int getRowCount() {
        return gosti.size();
    }

    @Override
    public int getColumnCount() {
       return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gost gost = gosti.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gost.getID();
            case 1:
                return gost.getIme();
            case 2:
                return gost.getPrezime();
            case 3:
                return gost.getBrojTelefona();

            default:
                return "Nema polja nigga";
        }
    }
    
}
