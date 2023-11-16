/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import static Modelo.Conexion.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author orepep
 */
public class ConexionBibliotecaria extends Conexion {
    
    public ArrayList<Bibliotecaria> obtenerTodasBibliotecarias() {
        ArrayList<Bibliotecaria> bibliotecarias = new ArrayList<>();
        String query = "SELECT * FROM Bibliotecaria";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String Dni = resultSet.getString("Dni");
                String Nombre = resultSet.getString("Nombre");

                Bibliotecaria bibliotecaria = new Bibliotecaria(Dni , Nombre);
                bibliotecarias.add(bibliotecaria);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            System.err.println("Error al obtener Bibliotecarias: " + e.getMessage());
        }

        return bibliotecarias;
    }
    
    public Bibliotecaria obtenerBibliotecariasPorDni(String Dni) {
        Bibliotecaria bibliotecaria = null;
        String query = "SELECT * FROM Bibliotecaria WHERE Dni = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el valor del parámetro DNI en el PreparedStatement
            statement.setString(1, Dni);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String Id = resultSet.getString("Dni");
                String nombre = resultSet.getString("Nombre");

                bibliotecaria = new Bibliotecaria(Dni , nombre);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener Bibliotecaria por Dni: " + e.getMessage());
        }

        return bibliotecaria;
    }

    
    public void eliminarBibliotecariaPorId(String Dni) {
        String query = "DELETE FROM Bibliotecaria WHERE Dni = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el parámetro en la consulta
            statement.setString(1, Dni);

            // Ejecuta la eliminación
            statement.executeUpdate();

            System.out.println("Bibliotecaria eliminada correctamente." + Dni);

            // Cierra los recursos
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error al eliminar Provedor: " + e.getMessage());
        }
    }
    public void insertarBibliotecaria(Bibliotecaria bibliotecaria) {
        String query = "INSERT INTO Bibliotecaria (Dni , Nombre) VALUES (? ,?)";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, bibliotecaria.getDni());
            statement.setString(2, bibliotecaria.getNombre());

            // Ejecuta la inserción
            statement.executeUpdate();

            System.out.println("Bibliotecaria insertada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al insertar Bibliotecaria: " + e.getMessage());
        }
    }
    
    public void ModificarBibliotecaria(String Dni , String Nombre) {
        String query = "UPDATE Bibliotecaria SET Nombre = ? WHERE Dni = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, Nombre);
            statement.setString(2, Dni);

            // Ejecuta la actualización
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Bibliotecaria modificada correctamente." + Dni);
            } else {
                System.out.println("No se encontró ningúna Bibliotecaria con el Dni proporcionado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al modificar Bibliotecaria: " + e.getMessage());
        }
    }
}
