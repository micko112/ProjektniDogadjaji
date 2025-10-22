/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModels;
import domain.Angazman;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author user
 */
public class TableModelAngazman extends AbstractTableModel {

    private ArrayList<Angazman> listaAngazmana;
    private final String[] kolone = {"Izvođač", "Žanr", "Trajanje (min)"};

    public TableModelAngazman() {
        this.listaAngazmana = new ArrayList<>();
    }

    public TableModelAngazman(ArrayList<Angazman> listaAngazmana) {
        this.listaAngazmana = listaAngazmana;
    }

    @Override
    public int getRowCount() { return listaAngazmana.size(); }
    @Override
    public int getColumnCount() { return kolone.length; }
    @Override
    public String getColumnName(int column) { return kolone[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Angazman a = listaAngazmana.get(rowIndex);
        switch (columnIndex) {
            case 0: return a.getIzvodjac().getIme();
            case 1: return a.getIzvodjac().getZanr();
            case 2: return a.getTrajanjeNastupa();
            default: return "N/A";
        }
    }
    
    public void dodajAngazman(Angazman a) {
        listaAngazmana.add(a);
        fireTableDataChanged();
    }
    
    public void obrisiAngazman(int red) {
        listaAngazmana.remove(red);
        fireTableDataChanged();
    }
    
    public ArrayList<Angazman> getListaAngazmana() {
        return listaAngazmana;
    }

    public Angazman getAngazman(int red) {
        Angazman a = listaAngazmana.get(red);
        return a;
    }
}