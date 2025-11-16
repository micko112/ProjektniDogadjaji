/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Izvodjac;
import model.Lokacija;
import model.Nastup;
import model.Lineup;
import Controller.Controller;
/**
 *
 * @author user
 */
public class NastupTableModel extends AbstractTableModel {
//dodaj nekad vreme od i vreme do 
    Controller controller = Controller.getInstance();
    List<Nastup> nastupi = new ArrayList<>();
      List<Izvodjac> izv;
    String kolone[] = {"Izvodjac",  "trajanje"};

    public NastupTableModel(List<Nastup> nastupi) {
        this.nastupi = nastupi;
        this.izv = controller.ucitajIzvodjaceIzBaze();
    }
  
   
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
//                                                                                                          dodaj vreme od i vreme do ako te ne mrzi
    @Override
    public int getRowCount() {
        return nastupi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
          Nastup nas = nastupi.get(rowIndex);
          String izvIme = "";
         for (Izvodjac izvodjac : izv) {
            if(izvodjac.getID()==nas.getIzvodjacID()){
                izvIme = izvodjac.getIme();
            }
            
        }
        switch (columnIndex) {
            
            case 0:
                return izvIme;
            case 1:
                return nas.getTrajanje();
            default:
                return "N/A";
        }
    }

}
