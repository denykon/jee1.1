package by.gsu.epamlab.model.connection;

import by.gsu.epamlab.model.exceptions.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection class
 */
public class DbConnection {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/todo";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws DAOException {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DAOException("Can't create connection to database" + e);
        }
    }

    public static <T extends AutoCloseable> void closeResources(T... resources) {
        for (T resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (SQLException e) {
                    System.err.println("Resource closing problem: " + e);
                } catch (Exception e) {
                    System.err.println("This resource can't be closed: " + e);
                }
            }
        }
    }
}
