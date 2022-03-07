package entidades;

public class Inscripcion {

	private int cod_inscripcion;
	private int id_curso;
	private int leg_alumno;
	
	public Inscripcion() {
	}
	
	public Inscripcion(int cod_inscripcion, int id_curso, int leg_alumno) {
		this.cod_inscripcion = cod_inscripcion;
		this.id_curso = id_curso;
		this.leg_alumno = leg_alumno;
	}

	public int getCod_inscripcion() {
		return cod_inscripcion;
	}

	public void setCod_inscripcion(int cod_inscripcion) {
		this.cod_inscripcion = cod_inscripcion;
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	public int getLeg_alumno() {
		return leg_alumno;
	}

	public void setLeg_alumno(int leg_alumno) {
		this.leg_alumno = leg_alumno;
	}	
	
	
}
