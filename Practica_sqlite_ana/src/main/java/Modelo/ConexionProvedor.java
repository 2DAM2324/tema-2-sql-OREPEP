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
public class ConexionProvedor extends Conexion{
    
    public ArrayList<Provedores> obtenerTodosProvedores() {
        ArrayList<Provedores> provedores = new ArrayList<>();
        String query = "SELECT * FROM Proveedor";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String Id = resultSet.getString("Id");

                Provedores provedor = new Provedores(Id);
                provedores.add(provedor);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener provedores: " + e.getMessage());
        }

        return provedores;
    }
    
    public Provedores obtenerProvedoresPorId(String Id2) {
        Provedores provedor = null;
        String query = "SELECT * FROM Proveedor WHERE Id = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el valor del parámetro DNI en el PreparedStatement
            statement.setString(1, Id2);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String Id = resultSet.getString("Id");

                provedor = new Provedores(Id);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener provedor por id: " + e.getMessage());
        }

        return provedor;
    }

    
    public void eliminarProvedorPorId(String Id) {
        String query = "DELETE FROM Proveedor WHERE Id = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el parámetro en la consulta
            statement.setString(1, Id);

            // Ejecuta la eliminación
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Provedor eliminado correctamente." + Id);

            // Cierra los recursos
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Provedor: " + e.getMessage());
        }
    }
    public void insertarProvedor(Provedores provedor) {
        String query = "INSERT INTO Proveedor (Id) VALUES (?)";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, provedor.getCodProvedor());

            // Ejecuta la inserción
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null,"Provedor insertado correctamente.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar Provedor: " + e.getMessage());
        }
    }
    
    public void ModificarProvedor(String Id , String id2) {
        String query = "UPDATE Proveedor SET Id = ? WHERE Id = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, id2);
            statement.setString(2, Id);

            // Ejecuta la actualización
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Provedor modificado correctamente." + id2);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún Provedor con el Id proporcionado.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar Provedor: " + e.getMessage());
        }
    }
}
