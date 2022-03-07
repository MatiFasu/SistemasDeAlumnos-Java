package servicio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Alumno;
import entidades.Curso;

public class CursoTableModel extends AbstractTableModel {
	
	private static final int COLUMNA_ID = 0;
	private static final int COLUMNA_CODMAT = 1;
	private static final int COLUMNA_LEGPROF = 2;
	private static final int COLUMNA_MATERIA = 3;
	private static final int COLUMNA_PRECIO = 4;
	private static final int COLUMNA_CUPO = 5;
	
	private String[] nombresColumnas = {"Id_Curso","Cod_Materia","Leg_Prof", "Materia", "Precio","Cupo"};

    private Class[] tiposColumna = {Integer.class, Integer.class, Integer.class, String.class, Float.class, Integer.class};

    private List<Curso> contenido;

    public CursoTableModel() {
        contenido = new ArrayList<Curso>();
    }

    public CursoTableModel(List<Curso> contenidoInicial) {
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

        Curso c = contenido.get(rowIndex);

        Object result = null;

        switch(columnIndex) {
            case COLUMNA_ID:
                result = c.getId_Curso();
                break;
            case COLUMNA_CODMAT:
                result = c.getCod_materia();
                break;
            case COLUMNA_LEGPROF:
                result = c.getLeg_profe();
                break;
            case COLUMNA_MATERIA:
                result = c.getNombreMateria();
                break;    
            case COLUMNA_PRECIO:
                result = c.getPrecio();
                break;
            case COLUMNA_CUPO:
                result = c.getCupo();
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

	public List<Curso> getContenido() {
		return contenido;
	}

	public void setContenido(List<Curso> contenido) {
		this.contenido = contenido;
	}
    

}
