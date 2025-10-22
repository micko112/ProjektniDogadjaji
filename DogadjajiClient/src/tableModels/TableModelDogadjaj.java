/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModels;
import controller.CController;
import domain.Dogadjaj;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author user
 */
public class TableModelDogadjaj extends AbstractTableModel {

    private ArrayList<Dogadjaj> listaDogadjaja;
    private final String[] kolone = {"ID", "Naziv", "Vreme početka", "Lokacija"};
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public TableModelDogadjaj() {
        try {
            this.listaDogadjaja = CController.getInstance().vratiSveDogadjaje();
        } catch (Exception e) {
            this.listaDogadjaja = new ArrayList<>();
        }
    }

    @Override
    public int getRowCount() { return listaDogadjaja.size(); }
    @Override
    public int getColumnCount() { return kolone.length; }
    @Override
    public String getColumnName(int column) { return kolone[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dogadjaj d = listaDogadjaja.get(rowIndex);
        switch (columnIndex) {
            case 0: return d.getDogadjajId();
            case 1: return d.getNaziv();
            case 2: return dtf.format(d.getDatumVreme());
            case 3: return d.getLokacija().getNaziv(); 
            default: return "N/A";
        }
    }
    
    public Dogadjaj getDogadjaj(int red) {
        return listaDogadjaja.get(red);
    }
    
    public void osveziTabelu() {
        try {
            listaDogadjaja = CController.getInstance().vratiSveDogadjaje();
            fireTableDataChanged();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
