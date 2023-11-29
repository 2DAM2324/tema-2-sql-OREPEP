package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    protected static Connection connection;

    public Conexion() {
    }

    public void getConexion() {
        if (connection == null) {
            try {
                // Establece la conexión con la base de datos y habilita las restricciones de clave externa
                connection = DriverManager.getConnection("jdbc:sqlite:/home/orepep/Bases_de_datos_sqlite/PracticaAna/PracticaAna.db?foreign_keys=true");
                JOptionPane.showMessageDialog(null,"Conexión exitosa a la base de datos SQLite.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
            }
        }
    }

    public void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                JOptionPane.showMessageDialog(null, "Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
