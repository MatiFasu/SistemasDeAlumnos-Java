package servicio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Reporte;

public class ReporteTableModel extends AbstractTableModel{
	
	private static final int COLUMNA_CURSO = 0;
	private static final int COLUMNA_ANOTADOS= 1;
    private static final int COLUMNA_RECAUDACION = 2;

    private String[] nombresColumnas = {"Id_Curso","Anotados","Recaudacion"};

    private Class[] tiposColumna = {Integer.class, Integer.class, Float.class};

    private List<Reporte> contenido;

    public ReporteTableModel() {
        contenido = new ArrayList<Reporte>();
    }

    public ReporteTableModel(List<Reporte> contenidoInicial) {
        contenido = contenidoInicial;
    }

    public int getRowCount() {
        return contenido.size();
    }

    public int getColumnCount() {
        return nombresColumnas.length;
    }

    public String getColumnName(int col) {
        return nombresColumnas[col];
    }

    public Class getColumnClass(int col) {
        return tiposColumna[col];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        Reporte r = contenido.get(rowIndex);

        Object result = null;

        switch(columnIndex) {
            case COLUMNA_CURSO:
                result = r.getId_Curso();
                break;
            case COLUMNA_ANOTADOS:
                result = r.getAnotados();
                break;
            case COLUMNA_RECAUDACION:
                result = r.getRecaudacion();
                break;
            default:
                result = new String("");
        }

        return result;
    }

    public String[] getNombresColumnas() {
        return nombresColumnas;
    }

    public void setNombresColumnas(String[] nombresColumnas) {
        this.nombresColumnas = nombresColumnas;
    }

    public Class[] getTiposColumna() {
        return tiposColumna;
    }

    public void setTiposColumna(Class[] tiposColumna) {
        this.tiposColumna = tiposColumna;
    }

    public List<Reporte> getContenido() {
        return contenido;
    }

    public void setContenido(List<Reporte> contenido) {
        this.contenido = contenido;
    }
	

}
