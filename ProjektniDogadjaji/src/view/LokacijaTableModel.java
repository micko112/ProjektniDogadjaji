/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Lokacija;

/**
 *
 * @author user
 */
public class LokacijaTableModel extends AbstractTableModel{
    
    private String[] kolone = {"ID", "naziv", "adresa", "kapacitet"};
    private List<Lokacija> lokacije = new ArrayList<>();

    public LokacijaTableModel(List<Lokacija> lokacije){
        this.lokacije=lokacije;
    }
    @Override
    public int getRowCount() {
    
     return   lokacije.size();
    }

    public Lokacija getLokacijaAt(int rowIndex){
       return  lokacije.get(rowIndex);
    }
    
    @Override
    public int getColumnCount() {
      return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      Lokacija lok = lokacije.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lok.getLokacijaID();
            case 1:
                return lok.getNaziv();
            case 2:
                return lok.getAdresa();
            case 3:
                return lok.getKapacitet();
            
            default:
                return "Nema tvog polja";
        }
      
    }

    @Override
    public String getColumnName(int column) {
       return kolone[column];
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

      void osveziPodatke() {
       fireTableDataChanged();
    }
    


    
    
    
    
}
