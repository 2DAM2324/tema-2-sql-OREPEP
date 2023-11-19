package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexionRevision extends Conexion {

    public ArrayList<RevisarMaterial> obtenerTodasRevisiones() {
        ArrayList<RevisarMaterial> revisiones = new ArrayList<>();
        String query = "SELECT * FROM Revision";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String idBibliotecaria = resultSet.getString("id_bibliotecaria");
                String idMaterialBibliografico = resultSet.getString("id_material_bibliografico");
                String fechaRevision = resultSet.getString("fecha_revision");
                String idString = String.valueOf(id);
                RevisarMaterial revision = new RevisarMaterial(idString, idBibliotecaria, idMaterialBibliografico, fechaRevision);
                revisiones.add(revision);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener revisiones: " + e.getMessage());
        }

        return revisiones;
    }

    public void eliminarRevisionPorId(int id) {
        String query = "DELETE FROM Revision WHERE id = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el parámetro en la consulta
            statement.setInt(1, id);

            // Ejecuta la eliminación
            statement.executeUpdate();

            System.out.println("Revisión eliminada correctamente. ID: " + id);

            // Cierra los recursos
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error al eliminar revisión: " + e.getMessage());
        }
    }

    public void insertarRevision(RevisarMaterial revision) {
        String query = "INSERT INTO Revision (id_bibliotecaria, id_material_bibliografico, fecha_revision) VALUES (?, ?, ?)";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, revision.getBibliotecaria());
            statement.setString(2, revision.getMaterialBibliografico());
            statement.setString(3, revision.getFechaInicioAsString());

            // Ejecuta la inserción
            statement.executeUpdate();

            System.out.println("Revisión insertada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al insertar revisión: " + e.getMessage());
        }
    }

    public void modificarRevision(int id, String idBibliotecaria, String idMaterialBibliografico) {
        String query = "UPDATE Revision SET id_bibliotecaria = ?, id_material_bibliografico = ? WHERE id = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetros en la consulta
            statement.setString(1, idBibliotecaria);
            statement.setString(2, idMaterialBibliografico);
            statement.setInt(4, id);

            // Ejecuta la actualización
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Revisión modificada correctamente. ID: " + id);
            } else {
                System.out.println("No se encontró ninguna revisión con el ID proporcionado.");
            }

            // Cierra los recursos
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al modificar revisión: " + e.getMessage());
        }
    }
    
    public RevisarMaterial obtenerRevisionPorId(int id) {
        RevisarMaterial revision = null;
        String query = "SELECT * FROM Revision WHERE id = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el valor del parámetro ID en el PreparedStatement
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String idBibliotecaria = resultSet.getString("id_bibliotecaria");
                String idMaterialBibliografico = resultSet.getString("id_material_bibliografico");
                String fechaRevision = resultSet.getString("fecha_revision");
                String idString = String.valueOf(id);
                revision = new RevisarMaterial(idString, idBibliotecaria, idMaterialBibliografico, fechaRevision);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error al obtener revisión por ID: " + e.getMessage());
        }

        return revision;
    }


}
