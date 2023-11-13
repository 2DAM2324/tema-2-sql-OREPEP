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
                String nombre = resultSet.getString("nombre_usuario");
                String apellidos = resultSet.getString("apellidos_usuario");
                String login = resultSet.getString("login_usuario");
                String password = resultSet.getString("password_usuario");

                Usuario usuario = new Usuario(nombre, apellidos, login, password);
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
    
    public void eliminarUsuarioPorId(String login) {
        String query = "DELETE FROM Usuarios WHERE login_usuario = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el parámetro en la consulta
            statement.setString(1, login);

            // Ejecuta la eliminación
            statement.executeUpdate();

            System.out.println("Usuario eliminado correctamente." + login);

            // Cierra los recursos
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
    public void insertarUsuario(Usuario usuario) {
        String query = "INSERT INTO Usuarios (nombre_usuario, apellidos_usuario, login_usuario, password_usuario) VALUES (?, ?, ?, ?)";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.get());
            statement.setString(3, usuario.getDni());
            statement.setString(4, usuario.get());

            // Ejecuta la inserción
            statement.executeUpdate();

            System.out.println("Usuario insertado correctamente.");

            // Cierra los recursos
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }
    
    
}
