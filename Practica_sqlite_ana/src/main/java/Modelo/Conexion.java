/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    protected static Connection connection;

    public Conexion() {
    }
    
    public void getConexion() {
        if (connection == null) {
            try {
                // Establece la conexión con la base de datos
                connection = DriverManager.getConnection("jdbc:sqlite:/home/orepep/Bases_de_datos_sqlite/PracticaAna/PracticaAna.db");
                System.out.println("Conexión exitosa a la base de datos SQLite.");
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        }
    }
    public  void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}


