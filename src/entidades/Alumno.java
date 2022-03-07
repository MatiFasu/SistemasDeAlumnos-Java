package entidades;

public class Alumno {
	
	private int leg_alumno;
	private String nombre;
	private String apellido;	
	private int dni;

	public Alumno() {
	}
	
	public Alumno(int leg_alumno, String nombre, String apellido, int dni) {
		this.leg_alumno = leg_alumno;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public int getLegAlum() {
		return leg_alumno;
	}

	public void setLegAlum(int leg_alumno) {
		this.leg_alumno = leg_alumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}
	
	
}
