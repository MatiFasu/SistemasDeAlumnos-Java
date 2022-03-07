package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Inscripcion;

public class InscripcionDAOH2Impl implements InscripcionDAO {

	@Override
	public void crearInscripcion(Inscripcion ins) throws DAOException {
		int codIns = ins.getCod_inscripcion();
        int idCurso = ins.getId_curso();
        int legAl = ins.getLeg_alumno();

        Connection c = DBManager.connect();
        try {
            String sql = "INSERT INTO inscripcion (cod_ins, id_Curso, leg_alum) VALUES ('" + codIns + "', '" + idCurso + "', '" + legAl + "' )";

            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en InscripcionDAOH2Impl.crearInscripcion -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.crearInscripcion -> Error Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.crearInscripcion -> Error al cerrar la conexion",e1);
            }
        }
	}

	@Override
	public void borrarInscripcion(int cod_ins) throws DAOException {
		String sql = "DELETE FROM inscripcion WHERE cod_ins = '" + cod_ins + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en InscripcionDAOH2Impl.borrarInscripcion -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.borrarInscripcion -> Error Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.borrarInscripcion -> Error al cerrar la conexion",e1);
            }
        }
	}

	@Override
	public void actualizarInscripcion(Inscripcion ins) throws DAOException {
		int codIns = ins.getCod_inscripcion();
        int idCurso = ins.getId_curso();
        int legAl = ins.getLeg_alumno();

        String sql = "UPDATE inscripcion set id_curso = '" + idCurso + "', leg_alum = '" + legAl + "' WHERE cod_ins = '" + codIns + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en InscripcionDAOH2Impl.actualizarInscripcion -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.actualizarInscripcion -> Error Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.actualizarInscripcion -> Error al cerrar la conexion",e1);
            }
        }
	}

	@Override
	public Inscripcion muestraInscripcion(int cod_ins) throws DAOException {
		String sql = "SELECT * FROM inscripcion WHERE cod_ins = '" + cod_ins + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int codI = rs.getInt("cod_ins");
                int id_curso = rs.getInt("id_curso");
                int leg_alum = rs.getInt("leg_alum");
                Inscripcion i = new Inscripcion(codI, id_curso, leg_alum);
                return i;
            }
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en InscripcionDAOH2Impl.muestraInscripcion -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.muestraInscripcion -> Error Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.muestraInscripcion -> Error al cerrar la conexion",e1);
            }
        }
        return null;
	}

	@Override
	public List<Inscripcion> listaTodasLasInscripciones() throws DAOException {
		List<Inscripcion> resultado = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
            	int codI = rs.getInt("cod_ins");
                int id_curso = rs.getInt("id_curso");
                int leg_alum = rs.getInt("leg_alum");
                Inscripcion i = new Inscripcion(codI, id_curso, leg_alum);
                resultado.add(i);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en InscripcionDAOH2Impl.listaTodasLasInscripciones -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.listaTodasLasInscripciones -> Error Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en InscripcionDAOH2Impl.listaTodasLasInscripciones -> Error al cerrar la conexion",e1);
            }
        }
        return resultado;
	}
	

}
