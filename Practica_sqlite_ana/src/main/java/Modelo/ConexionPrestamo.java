package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexionPrestamo extends Conexion {

    public ArrayList<Prestamo> obtenerTodosPrestamos() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        String query = "SELECT * FROM Prestamo";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String usuario = resultSet.getString("Id_usuario");
                String materialBibliografico = resultSet.getString("Id_Material_Bibliotecario");
                String fechaInicio = resultSet.getString("Fecha_inicio");
                String fechaVencimiento = resultSet.getString("Fecha_vencimiento");

                Prestamo prestamo = new Prestamo(id, usuario, materialBibliografico, fechaInicio, fechaVencimiento);
                prestamos.add(prestamo);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener préstamos: " + e.getMessage());
        }

        return prestamos;
    }

    public void eliminarPrestamoPorId(String id) {
        String query = "DELETE FROM Prestamo WHERE id = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el parámetro en la consulta
            statement.setString(1, id);

            // Ejecuta la eliminación
            statement.executeUpdate();

            System.out.println("Préstamo eliminado correctamente. ID: " + id);

            // Cierra los recursos
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error al eliminar préstamo: " + e.getMessage());
        }
    }

    public void insertarPrestamo(Prestamo prestamo) {
        String query = "INSERT INTO Prestamo (id, Fecha_inicio, Fecha_vencimiento, Id_usuario, Id_Material_Bibliotecario) VALUES (?, ?, ?, ?, ?)";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, prestamo.getId());
            statement.setString(2, prestamo.getFechaInicioAsString());
            statement.setString(3, prestamo.getFechaVencimiento());
            statement.setString(4, prestamo.getUsuario());
            statement.setString(5, prestamo.getMaterialBibliografico());

            // Ejecuta la inserción
            statement.executeUpdate();

            System.out.println("Préstamo insertado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al insertar préstamo: " + e.getMessage());
        }
    }

    public Prestamo obtenerPrestamoPorId(String id) {
        Prestamo prestamo = null;
        String query = "SELECT * FROM Prestamo WHERE id = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el valor del parámetro ID en el PreparedStatement
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String usuario = resultSet.getString("Id_usuario");
                String materialBibliografico = resultSet.getString("Id_Material_Bibliotecario");
                String fechaInicio = resultSet.getString("Fecha_inicio");
                String fechaVencimiento = resultSet.getString("Fecha_vencimiento");

                prestamo = new Prestamo(id, usuario, materialBibliografico, fechaInicio, fechaVencimiento);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener préstamo por ID: " + e.getMessage());
        }

        return prestamo;
    }
    
    public void modificarPrestamo(String id, String nuevoUsuario, String nuevoMaterialBibliografico, String nuevaFechaInicio, String nuevaFechaVencimiento) {
        String query = "UPDATE Prestamo SET Id_usuario = ?, Id_Material_Bibliotecario = ?, Fecha_inicio = ?, Fecha_vencimiento = ? WHERE id = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, nuevoUsuario);
            statement.setString(2, nuevoMaterialBibliografico);
            statement.setString(3, nuevaFechaInicio);
            statement.setString(4, nuevaFechaVencimiento);
            statement.setString(5, id);

            // Ejecuta la actualización
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Préstamo modificado correctamente. ID: " + id);
            } else {
                System.out.println("No se encontró ningún préstamo con el ID proporcionado.");
            }

            // Cierra los recursos
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al modificar préstamo: " + e.getMessage());
        }
    }
}
