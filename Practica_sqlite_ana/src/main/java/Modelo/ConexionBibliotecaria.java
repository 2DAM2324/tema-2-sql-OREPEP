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
import javax.swing.JOptionPane;

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

                Bibliotecaria bibliotecaria = new Bibliotecaria(Dni);
                bibliotecarias.add(bibliotecaria);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error al obtener Bibliotecarias: " + e.getMessage());
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

                bibliotecaria = new Bibliotecaria(Dni);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener Bibliotecaria por Dni: " + e.getMessage());
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

            JOptionPane.showMessageDialog(null, "Bibliotecaria eliminada correctamente." + Dni);

            // Cierra los recursos
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Provedor: " + e.getMessage());
        }
    }
    public void insertarBibliotecaria(Bibliotecaria bibliotecaria) {
        String query = "INSERT INTO Bibliotecaria (Dni , Nombre ) VALUES (? , null)";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, bibliotecaria.getDni());

            // Ejecuta la inserción
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Bibliotecaria insertada correctamente.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar Bibliotecaria: " + e.getMessage());
        }
    }
    
    public void ModificarBibliotecaria(String Dni , String Dni2) {
        String query = "UPDATE Bibliotecaria SET Dni = ? WHERE Dni = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, Dni2);
            statement.setString(2, Dni);

            // Ejecuta la actualización
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Bibliotecaria modificada correctamente." + Dni);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningúna Bibliotecaria con el Dni proporcionado.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar Bibliotecaria: " + e.getMessage());
        }
    }
}
