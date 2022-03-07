package servicio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Curso;
import entidades.Inscripcion;
import entidades.Reporte;

public class InscribirseTableModel extends AbstractTableModel {
	
	private static final int COLUMNA_COD_INSCRIPCION = 0;
	private static final int COLUMNA_ID_CURSO= 1;
    private static final int COLUMNA_LEG_ALUMNO = 2;

    private String[] nombresColumnas = {"Id_Curso","Anotados","Recaudacion"};

    private Class[] tiposColumna = {Integer.class, Integer.class, Integer.class};

    private List<Inscripcion> contenido;
    private List<Curso> contenidoCurso;

    public InscribirseTableModel() {
        contenido = new ArrayList<Inscripcion>();
    }

    public InscribirseTableModel(List<Inscripcion> contenidoInicial) {
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

    	Inscripcion i = contenido.get(rowIndex);

        Object result = null;

        switch(columnIndex) {
            case COLUMNA_COD_INSCRIPCION:
                result = i.getCod_inscripcion();
                break;
            case COLUMNA_ID_CURSO:
                result = i.getId_curso();
                break;
            case COLUMNA_LEG_ALUMNO:
                result = i.getLeg_alumno();
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

    public List<Inscripcion> getContenido() {
        return contenido;
    }

    public void setContenido(List<Inscripcion> contenido) {
        this.contenido = contenido;
    }
    
    public List<Curso> getContenidoCurso() {
        return contenidoCurso;
    }

    public void setContenidoCurso(List<Curso> contenidoCurso) {
        this.contenidoCurso = contenidoCurso;
    }

}
