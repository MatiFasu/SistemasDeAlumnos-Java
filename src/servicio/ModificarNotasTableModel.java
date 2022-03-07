package servicio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Alumno;
import entidades.Notas;

public class ModificarNotasTableModel extends AbstractTableModel {
	
	private static final int COLUMNA_IDCURSO = 0;
    private static final int COLUMNA_CODMAT = 1;
    private static final int COLUMNA_LEGALUM = 2;
    private static final int COLUMNA_NOTAS = 3;

    private String[] nombresColumnas = {"Id_Curso","Cod_Materia","Leg_Alum","Notas"};

    private Class[] tiposColumna = {Integer.class, Integer.class, Integer.class, Float.class};

    private List<Notas> contenido;

    public ModificarNotasTableModel() {
        contenido = new ArrayList<Notas>();
    }

    public ModificarNotasTableModel(List<Notas> contenidoInicial) {
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

        Notas n = contenido.get(rowIndex);

        Object result = null;

        switch(columnIndex) {
            case COLUMNA_IDCURSO:
                result = n.getId_curso();
                break;
            case COLUMNA_CODMAT:
                result = n.getCod_mat();
                break;
            case COLUMNA_LEGALUM:
                result = n.getLeg_alum();
                break;
            case COLUMNA_NOTAS:
                result = n.getNota();
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

	public List<Notas> getContenido() {
		return contenido;
	}

	public void setContenido(List<Notas> contenido) {
		this.contenido = contenido;
	}
	

}
