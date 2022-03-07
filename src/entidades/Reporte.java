package entidades;

public class Reporte {
	
	private int id_Curso;
	private int anotados;
	private float recaudacion;
	
	public Reporte() {
		
	}
	
	public Reporte(int id_Curso, int anotados, float recaudacion) {
		super();
		this.id_Curso = id_Curso;
		this.anotados = anotados;
		this.recaudacion = recaudacion;
	}
	
	public int getId_Curso() {
		return id_Curso;
	}
	public void setId_Curso(int id_Curso) {
		this.id_Curso = id_Curso;
	}
	public int getAnotados() {
		return anotados;
	}
	public void setAnotados(int anotados) {
		this.anotados = anotados;
	}
	public float getRecaudacion() {
		return recaudacion;
	}
	public void setRecaudacion(float recaudacion) {
		this.recaudacion = recaudacion;
	}
	
	
	
}
