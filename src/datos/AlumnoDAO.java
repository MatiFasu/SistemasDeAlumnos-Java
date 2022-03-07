package datos;

import java.util.List;

import entidades.Alumno;

public interface AlumnoDAO {
	
	void crearAlumno(Alumno alumno) throws DAOException;

    void borrarAlumno(int leg) throws DAOException;

    void actualizarAlumno(Alumno alumno) throws DAOException;

    Alumno muestraAlumno(int leg) throws DAOException;

    List<Alumno> listaTodosLosAlumnos() throws DAOException;

}
