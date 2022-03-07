package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_BASE_URL = "jdbc:h2:tcp://localhost//C://Users//Matias//Desktop//Matias//Programas//TP//Project-TP//h2//base_de_datos//ejemplo";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection connect() throws DAOException {
        Connection c = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Falla en DBManager.Connection -> Driver no cargada",e);
        }
        try {
            c = DriverManager.getConnection(DB_BASE_URL, DB_USERNAME, DB_PASSWORD);
            c.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("Falla en DBManager.Connection -> No se obtuvo conexion",e);
        }

        return c;
    }
	
}
