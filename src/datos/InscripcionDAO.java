package datos;

import java.util.List;

import entidades.Inscripcion;

public interface InscripcionDAO {

	void crearInscripcion(Inscripcion ins) throws DAOException;

    void borrarInscripcion(int cod_ins) throws DAOException;

    void actualizarInscripcion(Inscripcion ins) throws DAOException;

    Inscripcion muestraInscripcion(int cod_ins) throws DAOException; 

    List<Inscripcion> listaTodasLasInscripciones() throws DAOException;
	
}
