package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Alumno;

public class AlumnoDAOH2Impl implements  AlumnoDAO {
	
	public void crearAlumno(Alumno alumno) throws DAOException {
        int leg = alumno.getLegAlum();
        String nom = alumno.getNombre();
        String ape = alumno.getApellido();
        int dni = alumno.getDni();

        Connection c = DBManager.connect();
        try {
            String sql = "INSERT INTO alumnos (leg, nombre, apellido, dni) VALUES ('" + leg + "', '" + nom + "', '" + ape + "', '" + dni + "' )";

            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
            	c.rollback();
            	throw new DAOException("Falla en AlumnoDAOH2Impl.crearAlumno -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.crearAlumno -> Falla Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.createAlumno -> Problema al cerrar la conexion",e1);
            }
        }
    }

    public void borrarAlumno(int leg) throws DAOException {
        String sql = "DELETE FROM alumnos WHERE leg = '" + leg + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en AlumnoDAOH2Impl.borrarAlumno -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.borrarAlumno -> Falla Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.createAlumno -> Problema al cerrar la conexion",e1);
            }
        }
    }

    public void actualizarAlumno(Alumno alumno) throws DAOException {
        int leg = alumno.getLegAlum();
        String nom = alumno.getNombre();
        String ape = alumno.getApellido();
        int dni = alumno.getDni();

        String sql = "UPDATE alumnos set nombre = '" + nom + "', apellido = '" + ape + "', dni = '" + dni +"' WHERE leg = '" + leg + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en AlumnoDAOH2Impl.actualizarAlumno -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.actualizarAlumno -> Falla Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.actualizarAlumno -> Problema al cerrar la conexion",e1);
            }
        }
    }

    public Alumno muestraAlumno(int leg) throws DAOException {
        String sql = "SELECT * FROM alumnos WHERE leg = '" + leg + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                int l = rs.getInt("leg");
                String nombre = rs.getString("nombre");
                String ape = rs.getString("apellido");
                int dni = rs.getInt("dni");
                Alumno a = new Alumno(l, nombre, ape, dni);
                return a;
            }
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en AlumnoDAOH2Impl.muestraAlumno -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.muestraAlumno -> Falla Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.muestraAlumno -> Problema al cerrar la conexion",e1);
            }
        }
        return null;
    }

    public List<Alumno> listaTodosLosAlumnos() throws DAOException {
        List<Alumno> resultado = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int leg = rs.getInt("leg");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int dni = rs.getInt("dni");
                Alumno a = new Alumno(leg, nombre, apellido, dni);
                resultado.add(a);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en AlumnoDAOH2Impl.listaTodosLosAlumno -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.listaTodosLosAlumno -> Falla Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en AlumnoDAOH2Impl.listaTodosLosAlumno -> Problema al cerrar la conexion",e1);
            }
        }
        return resultado;
    }
	
}
