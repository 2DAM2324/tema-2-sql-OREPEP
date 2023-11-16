/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author orepep
 */
public class ConexionUsuario extends Conexion{
    
    public ArrayList<Usuario> obtenerTodosUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuarios";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String Dni = resultSet.getString("Dni");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String edad = resultSet.getString("edad");

                Usuario usuario = new Usuario(Dni, nombre, telefono, edad);
                usuarios.add(usuario);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }

        return usuarios;
    }
    
    public Usuario obtenerUsuarioPorDni(String dni2) {
        Usuario usuario = null;
        String query = "SELECT * FROM Usuarios WHERE Dni = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el valor del parámetro DNI en el PreparedStatement
            statement.setString(1, dni2);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String dni = resultSet.getString("Dni");
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("telefono");
                String edad = resultSet.getString("edad");

                usuario = new Usuario(dni, nombre, telefono, edad);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por DNI: " + e.getMessage());
        }

        return usuario;
    }

    
    public void eliminarUsuarioPorId(String Dni) {
        String query = "DELETE FROM Usuarios WHERE Dni = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el parámetro en la consulta
            statement.setString(1, Dni);

            // Ejecuta la eliminación
            statement.executeUpdate();

            System.out.println("Usuario eliminado correctamente." + Dni);

            // Cierra los recursos
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
    public void insertarUsuario(Usuario usuario) {
        String query = "INSERT INTO Usuarios (Dni , nombre , telefono , edad) VALUES (?, ?, ?, ?)";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, usuario.getDni());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getTelefono());
            statement.setString(4, usuario.getEdad());

            // Ejecuta la inserción
            statement.executeUpdate();

            System.out.println("Usuario insertado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }
    
    public void ModificarCliente(String DniCliente, String nombreCliente, String TelefonoCliente, String EdadCliente) {
        String query = "UPDATE Usuarios SET nombre = ?, telefono = ?, edad = ? WHERE Dni = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, nombreCliente);
            statement.setString(2, TelefonoCliente);
            statement.setString(3, EdadCliente);
            statement.setString(4, DniCliente);

            // Ejecuta la actualización
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Usuario modificado correctamente." + DniCliente);
            } else {
                System.out.println("No se encontró ningún usuario con el DNI proporcionado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al modificar usuario: " + e.getMessage());
        }
    }
    
}
