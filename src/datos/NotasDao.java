package datos;

import java.util.List;

import entidades.Notas;

public interface NotasDao {
	
	void crearNota(Notas n) throws DAOException ;

    void actualizarNota(Notas n) throws DAOException ;

    List<Notas> listaTodasLasNotas() throws DAOException ;
	
}
