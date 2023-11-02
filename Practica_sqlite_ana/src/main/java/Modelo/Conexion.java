/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author orepep
 */
public class Conexion {
    
    Connection connection;

    public Conexion() {
        connection = getConexion();
    }

    public Connection getConexion() {
        Connection connection = null;
        try {
            // Establece la conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:/home/orepep/Bases_de_datos_sqlite/PracticaAna.db");
            System.out.println("Conexión exitosa a la base de datos SQLite.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        return connection; // Devuelve la conexión para que puedas usarla en otros métodos
    }
    
    
}


