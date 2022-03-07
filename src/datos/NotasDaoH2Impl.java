package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Notas;

public class NotasDaoH2Impl implements NotasDao{

	@Override
	public void crearNota(Notas n) throws DAOException {
		int cod_nota = n.getCod_nota();
		int id_curso = n.getId_curso();
		int cod_mat = n.getCod_mat();
		int leg_alum = n.getLeg_alum();
        float nota = n.getNota();

        Connection c = DBManager.connect();
        try {
            String sql = "INSERT INTO notas (cod_nota, id_curso, cod_mat, leg_alum, nota) VALUES ('" + cod_nota + "', '" + id_curso + "', '" + cod_mat + "', '" + leg_alum + "', '" + nota + "')";

            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en NotasDaoH2Impl.crearNota -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en NotasDaoH2Impl.crearNota -> Error Rollback",e1);
            }
            //throw new DAOException(e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en NotasDaoH2Impl.crearNota -> Error al cerrar la conexion",e1);
            }
        }	
	}

	@Override
	public void actualizarNota(Notas n) throws DAOException {
		int cod_nota = n.getCod_nota();
		int id_curso = n.getId_curso();
		int cod_mat = n.getCod_mat();
        int leg_alum = n.getLeg_alum();
        float nota = n.getNota();

        String sql = "UPDATE notas set id_curso = '" + id_curso + "', cod_mat = '" + cod_mat + "', leg_alum = '" + leg_alum + "', nota = '" + nota + "' WHERE cod_nota = '" + cod_nota + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en NotasDaoH2Impl.actualizarNota -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en NotasDaoH2Impl.actualizarNota -> Error Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en NotasDaoH2Impl.actualizarNota -> Error al cerrar la conexion",e1);
            }
        }
	}

	@Override
	public List<Notas> listaTodasLasNotas() throws DAOException {
		List<Notas> resultado = new ArrayList<>();
        String sql = "SELECT * FROM notas";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
            	int cod_nota = rs.getInt("cod_nota");
            	int id_curso = rs.getInt("id_curso");
            	int cod_mat = rs.getInt("cod_mat");
                int leg_alum = rs.getInt("leg_alum");
                float nota = rs.getFloat("nota");
                Notas i = new Notas(cod_nota,id_curso,cod_mat,leg_alum,nota);
                resultado.add(i);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException("Falla en NotasDaoH2Impl.listaTodasLasNotas -> No se pudo ejecutar la consulta",e);
            } catch (SQLException e1) {
            	throw new DAOException("Falla en NotasDaoH2Impl.listaTodasLasNotas -> Error Rollback",e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Falla en NotasDaoH2Impl.listaTodasLasNotas -> Error al cerrar la conexion",e1);
            }
        }
        return resultado;
	}

}
