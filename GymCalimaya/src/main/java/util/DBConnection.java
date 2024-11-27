package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/base_calimaya";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    static {
        try {
            // Registrar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL registrado correctamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al registrar el driver MySQL: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
