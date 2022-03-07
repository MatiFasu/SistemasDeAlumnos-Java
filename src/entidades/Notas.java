package entidades;

public class Notas {
	
	private int cod_nota;
	private int id_curso;
	private int cod_mat;
	private int leg_alum;
	private float nota;
	
	public Notas() {
	}
	
	public Notas(int cod_nota, int id, int cod_mat, int leg_alum, float nota) {
		this.cod_nota = cod_nota;
		this.id_curso = id;
		this.cod_mat = cod_mat;
		this.leg_alum = leg_alum;
		this.nota = nota;
	}
	
	public int getCod_nota() {
		return cod_nota;
	}

	public void setCod_nota(int cod_nota) {
		this.cod_nota = cod_nota;
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	public int getCod_mat() {
		return cod_mat;
	}

	public void setCod_mat(int cod_mat) {
		this.cod_mat = cod_mat;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public int getLeg_alum() {
		return leg_alum;
	}

	public void setLeg_alum(int leg_alum) {
		this.leg_alum = leg_alum;
	}
	
	
	
}
