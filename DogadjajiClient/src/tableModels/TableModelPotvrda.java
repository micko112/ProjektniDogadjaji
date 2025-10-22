/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModels;
import domain.Potvrda;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author user
 */
public class TableModelPotvrda extends AbstractTableModel {

    private ArrayList<Potvrda> listaPotvrda;
    private final String[] kolone = {"Gost", "Telefon", "Dolazi"};

    public TableModelPotvrda() {
        this.listaPotvrda = new ArrayList<>();
    }
    
    public TableModelPotvrda(ArrayList<Potvrda> listaPotvrda) {
        this.listaPotvrda = listaPotvrda;
    }

    @Override
    public int getRowCount() { return listaPotvrda.size(); }
    @Override
    public int getColumnCount() { return kolone.length; }
    @Override
    public String getColumnName(int column) { return kolone[column]; }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 2) return Boolean.class;
        return String.class;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Potvrda p = listaPotvrda.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getGost().getIme() + " " + p.getGost().getPrezime();
            case 1: return p.getGost().getTelefon();
            case 2: return p.isStatus();
            default: return "N/A";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
        Potvrda p = listaPotvrda.get(rowIndex);
        p.setStatus((Boolean) aValue); 
       
        p.setDatumVreme(LocalDateTime.now()); 
    }
    }
    
    public void dodajPotvrdu(Potvrda p) {
        listaPotvrda.add(p);
        fireTableDataChanged();
    }
    
    public void obrisiPotvrdu(int red) {
        listaPotvrda.remove(red);
        fireTableDataChanged();
    }
    
    public ArrayList<Potvrda> getListaPotvrda() {
        return listaPotvrda;
    }
}
