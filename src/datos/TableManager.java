package datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

	public void createAlumnoTable() throws DAOException {
        Connection c = DBManager.connect();

        String sql = "CREATE TABLE alumnos ( leg INTEGER IDENTITY, nombre VARCHAR(256), apellido VARCHAR(256), dni INTEGER) ";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch(SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en TableManager.createAlumnoTable -> No se pudo ejecutar la consulta",e);
            } catch(SQLException ex) {
                throw new DAOException("Falla en TableManager.createAlumnoTable -> Falla Rollback",ex); 
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
            	throw new DAOException("Falla en TableManager.createAlumnoTable -> Problema al cerrar la conexion",e);
            }
        }
    }

	public void createCursoTable() throws DAOException {
        Connection c = DBManager.connect();

        String sql = "CREATE TABLE cursos ( id_Curso INTEGER IDENTITY, cod_Mat INTEGER, leg_Prof INTEGER, mat VARCHAR(256), precio FLOAT, cupo INTEGER) ";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch(SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en TableManager.createCursoTable -> No se pudo ejecutar la consulta",e);
            } catch(SQLException ex) {
            	throw new DAOException("Falla en TableManager.createCursoTable -> Falla Rollback",ex); 
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
            	throw new DAOException("Falla en TableManager.createCursoTable -> Problema al cerrar la conexion",e);
            }
        }
    }
	
	public void createInscripcionTable() throws DAOException {
		Connection c = DBManager.connect();

        String sql = "CREATE TABLE inscripcion ( cod_ins INTEGER IDENTITY, id_curso INTEGER, leg_alum INTEGER) ";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch(SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en TableManager.createInscripcionTable -> No se pudo ejecutar la consulta",e);
            } catch(SQLException ex) {
            	throw new DAOException("Falla en TableManager.createInscripcionTable -> Falla Rollback",ex); 
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
            	throw new DAOException("Falla en TableManager.createCursoTable -> Problema al cerrar la conexion",e);
            }
        }
	}
	
	public void createNotasTable() throws DAOException {
        Connection c = DBManager.connect();

        String sql = "CREATE TABLE notas ( cod_nota INTEGER IDENTITY, id_curso INTEGER, cod_mat INTEGER, leg_alum INTEGER, nota FLOAT) ";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
        } catch(SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en TableManager.createNotasTable -> No se pudo ejecutar la consulta",e);
            } catch(SQLException ex) {
            	throw new DAOException("Falla en TableManager.createNotasTable -> Falla Rollback",ex);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
            	throw new DAOException("Falla en TableManager.createNotasTable -> Problema al cerrar la conexion",e);
            }
        }
    }
	
}
