package iei.proyecto.monumentos.BDconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDconexion {
    // Información de conexión
    private static final String URL = "jdbc:mysql://databaseiei.cn0ai6cmskk8.eu-west-3.rds.amazonaws.com:3306/";
    private static final String USER = "admin";
    private static final String PASSWORD = "alcachofaIEI";

    // Método para obtener una conexión
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Registrar el controlador de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos.");
            e.printStackTrace();
            throw e; // Lanzar la excepción para manejarla externamente si es necesario
        }
        return connection;
    }

    // Método principal para pruebas
    public static void main(String[] args) {
        try {
            Connection connection = BDconexion.getConnection();
            if (connection != null) {
                System.out.println("Conexión establecida correctamente.");
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("No se pudo establecer la conexión.");
        }
    }
}
