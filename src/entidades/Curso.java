package entidades;

public class Curso {
	
	private int id_curso;
	private int cod_materia;
	private int leg_profe;
	private String nombreMateria;
	private float precio;
	private int cupo;
	
	private static final int aprobacion = 6;

	public Curso() {
	}
	
	public Curso(int id_Curso, int cod_materia, int leg_profe, String nombreMateria, float precio, int cupo) {
		super();
		this.id_curso = id_Curso;
		this.cod_materia = cod_materia;
		this.leg_profe = leg_profe;
		this.nombreMateria = nombreMateria;
		this.precio = precio;
		this.cupo = cupo;
	}

	public int getId_Curso() {
		return id_curso;
	}

	public void setId_Curso(int id_Curso) {
		this.id_curso = id_Curso;
	}

	public int getCod_materia() {
		return cod_materia;
	}

	public void setCod_materia(int cod_materia) {
		this.cod_materia = cod_materia;
	}

	public int getLeg_profe() {
		return leg_profe;
	}

	public void setLeg_profe(int leg_profe) {
		this.leg_profe = leg_profe;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	
	
	
}
