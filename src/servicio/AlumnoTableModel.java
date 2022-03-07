package servicio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Alumno;

public class AlumnoTableModel extends AbstractTableModel {

	private static final int COLUMNA_LEG = 0;
    private static final int COLUMNA_NOMBRE = 1;
    private static final int COLUMNA_APELLIDO = 2;
    private static final int COLUMNA_DNI = 3;

    private String[] nombresColumnas = {"Legajo","Nombre","Apellido","Dni"};

    private Class[] tiposColumna = {Integer.class, String.class, String.class, Integer.class};

    private List<Alumno> contenido;

    public AlumnoTableModel() {
        contenido = new ArrayList<Alumno>();
    }

    public AlumnoTableModel(List<Alumno> contenidoInicial) {
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

        Alumno a = contenido.get(rowIndex);

        Object result = null;

        switch(columnIndex) {
            case COLUMNA_LEG:
                result = a.getLegAlum();
                break;
            case COLUMNA_NOMBRE:
                result = a.getNombre();
                break;
            case COLUMNA_APELLIDO:
                result = a.getApellido();
                break;
            case COLUMNA_DNI:
                result = a.getDni();
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

    public List<Alumno> getContenido() {
        return contenido;
    }

    public void setContenido(List<Alumno> contenido) {
        this.contenido = contenido;
    }
	
}
