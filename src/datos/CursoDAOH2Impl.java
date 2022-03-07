package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Curso;

public class CursoDAOH2Impl implements  CursoDAO {

	@Override
	public void crearCurso(Curso curso) throws DAOException {
		int id_curso = curso.getId_Curso();
		int cod_materia = curso.getCod_materia();
		int leg_prof = curso.getLeg_profe();
		String mat = curso.getNombreMateria();
		float precio = curso.getPrecio();
		int cupo = curso.getCupo();
		
        Connection c = DBManager.connect();
        try {
            String sql = "INSERT INTO cursos (id_Curso, cod_Mat, leg_Prof, mat, precio, cupo) VALUES ('" + id_curso + "', '" + cod_materia + "', '" + leg_prof + "', '" + mat + "', '" + precio + "', '" + cupo + "' )";

            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en CursoDAOH2Impl.crearCurso -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.crearCurso -> Falla Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.crearCurso -> Error al cerrar la conexion",e1);
            }
        }
		
	}

	@Override
	public void borrarCurso(int id) throws DAOException {
		String sql = "DELETE FROM cursos WHERE id_Curso = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en CursoDAOH2Impl.borrarCurso -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.borrarCurso -> Error Rollback",e1);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.borrarCurso -> Error al cerrar la conexion ",e1);
            }
        }
        }
	}

	@Override
	public void actualizarCurso(Curso curso) throws DAOException {
		int id_curso = curso.getId_Curso();
		int cod_materia = curso.getCod_materia();
		int leg_prof = curso.getLeg_profe();
		String mat = curso.getNombreMateria();
		float precio = curso.getPrecio();
		int cupo = curso.getCupo();

        String sql = "UPDATE cursos set cod_Mat = '" + cod_materia + "', leg_Prof = '" + leg_prof + "', mat = '" + mat + "', precio = '" + precio + "', cupo = '" + cupo + "' WHERE id_Curso = '" + id_curso + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en CursoDAOH2Impl.actualizarCurso -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.actualizarCurso -> Error Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.actualizarCurso -> Error al cerrar la conexion",e1);
            }
        }
		
	}

	@Override
	public Curso muestraCurso(int id) throws DAOException {
		String sql = "SELECT * FROM cursos WHERE id_Curso = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int idC = rs.getInt("id_Curso");
                int cod = rs.getInt("cod_Mat");
                int leg = rs.getInt("leg_Prof");
                String mat = rs.getString("mat");
                float p = rs.getFloat("precio");
                int cupo = rs.getInt("cupo");
                Curso curso = new Curso(idC, cod, leg, mat, p, cupo);
                return curso;
            }
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en CursoDAOH2Impl.muestraCurso -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.muestraCurso -> Error rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.muestraCurso -> Error al cerrar la conexion",e1);
            }
        }
        return null;
	}

	@Override
	public List<Curso> listaTodosLosCursos() throws DAOException {
		List<Curso> resultado = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
            	int idC = rs.getInt("id_Curso");
                int cod = rs.getInt("cod_Mat");
                int leg = rs.getInt("leg_Prof");
                String m = rs.getString("mat");
                float p = rs.getFloat("precio");
                int cupo = rs.getInt("cupo");
                Curso curso = new Curso(idC, cod, leg, m, p, cupo);
                resultado.add(curso);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en CursoDAOH2Impl.listaTodosLosCursos -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.listaTodosLosCursos -> Error Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en CursoDAOH2Impl.listaTodosLosCursos -> Error al cerrar la conexion",e1);
            }
        }
        return resultado;
	}
	
	

}
