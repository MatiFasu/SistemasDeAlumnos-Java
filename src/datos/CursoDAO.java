package datos;

import java.util.List;

import entidades.Curso;

public interface CursoDAO {

	void crearCurso(Curso curso) throws DAOException;

    void borrarCurso(int id) throws DAOException;

    void actualizarCurso(Curso curso) throws DAOException;

    Curso muestraCurso(int id) throws DAOException;

    List<Curso> listaTodosLosCursos() throws DAOException;
	
}
