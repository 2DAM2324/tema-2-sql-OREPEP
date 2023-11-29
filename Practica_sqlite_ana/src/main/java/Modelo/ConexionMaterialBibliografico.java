/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author orepep
 */
public class ConexionMaterialBibliografico extends Conexion{
    
    public ArrayList<Libro> obtenerTodosLibros() {
        ArrayList<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM Libro";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String isbn = resultSet.getString("ISBN");
                String titulo = resultSet.getString("Titulo");
                String autor = resultSet.getString("Autor");
                String proveedor = resultSet.getString("Provedor");

                Libro libro = new Libro(isbn, titulo, autor, proveedor);
                libros.add(libro);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener libros: " + e.getMessage());
        }

        return libros;
    }

    public ArrayList<Tesis> obtenerTodasTesis() {
        ArrayList<Tesis> tesisList = new ArrayList<>();
        String query = "SELECT * FROM Tesis";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String doi = resultSet.getString("DOI");
                String titulo = resultSet.getString("Titulo");
                String autor = resultSet.getString("Autor");
                String proveedor = resultSet.getString("Provedor");

                Tesis tesis = new Tesis(doi, titulo, autor, proveedor);
                tesisList.add(tesis);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al obtener tesis: " + e.getMessage());
        }

        return tesisList;
    }
    
        public void eliminarLibroPorISBN(String isbn) {
        try {
            // Inicia la transacción
            connection.setAutoCommit(false);

            // Elimina primero de la tabla hija (Libro)
            eliminarLibro(isbn);

            // Luego elimina de la tabla padre (Material_Bibliografico)
            eliminarMaterialBibliografico(isbn);

            // Confirma la transacción
            connection.commit();

            JOptionPane.showMessageDialog(null,"Libro eliminado correctamente: " + isbn);
        } catch (SQLException e) {
            // Si hay un error, realiza un rollback
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null,"Error al eliminar libro: " + e.getMessage());
            } catch (SQLException rollbackException) {
                JOptionPane.showMessageDialog(null,"Error al realizar rollback: " + rollbackException.getMessage());
            }
        } finally {
            // Restaura el modo de autocommit
            try {
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                JOptionPane.showMessageDialog(null, "Error al restaurar el modo de autocommit: " + autoCommitException.getMessage());
            }
        }
    }

    public void eliminarTesisPorDOI(String doi) {
        try {
            // Inicia la transacción
            connection.setAutoCommit(false);

            // Elimina primero de la tabla hija (Tesis)
            eliminarTesis(doi);

            // Luego elimina de la tabla padre (Material_Bibliografico)
            eliminarMaterialBibliografico(doi);

            // Confirma la transacción
            connection.commit();

            JOptionPane.showMessageDialog(null, "Tesis eliminada correctamente: " + doi);
        } catch (SQLException e) {
            // Si hay un error, realiza un rollback
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Error al eliminar tesis: " + e.getMessage());
            } catch (SQLException rollbackException) {
                JOptionPane.showMessageDialog(null,"Error al realizar rollback: " + rollbackException.getMessage());
            }
        } finally {
            // Restaura el modo de autocommit
            try {
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                JOptionPane.showMessageDialog(null, "Error al restaurar el modo de autocommit: " + autoCommitException.getMessage());
            }
        }
    }
    
    private void eliminarLibro(String isbn) throws SQLException {
        String query = "DELETE FROM Libro WHERE ISBN = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, isbn);
        statement.executeUpdate();
        statement.close();
    }

    private void eliminarTesis(String doi) throws SQLException {
        String query = "DELETE FROM Tesis WHERE Doi = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, doi);
        statement.executeUpdate();
        statement.close();
    }

    private void eliminarMaterialBibliografico(String id) throws SQLException {
        String query = "DELETE FROM Material_Bibliografico WHERE Id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.executeUpdate();
        statement.close();
    }
    
    public void insertarLibro(Libro libro) {
        try {
            // Inicia la transacción
            connection.setAutoCommit(false);
            
            // Inserta en la tabla padre (Material_Bibliografico)
            insertarMaterialBibliografico(libro.getIsbn());
            
            // Inserta en la tabla hija (Libro)
            insertarLibroEnTabla(libro);

            

            // Confirma la transacción
            connection.commit();

            JOptionPane.showMessageDialog(null, "Libro insertado correctamente.");
        } catch (SQLException e) {
            // Si hay un error, realiza un rollback
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null,"Error al insertar libro: " + e.getMessage());
            } catch (SQLException rollbackException) {
                JOptionPane.showMessageDialog(null, "Error al realizar rollback: " + rollbackException.getMessage());
            }
        } finally {
            // Restaura el modo de autocommit
            try {
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                JOptionPane.showMessageDialog(null, "Error al restaurar el modo de autocommit: " + autoCommitException.getMessage());
            }
        }
    }

    public void insertarTesis(Tesis tesis) {
        try {
            // Inicia la transacción
            connection.setAutoCommit(false);
            
            // Inserta en la tabla padre (Material_Bibliografico)
            insertarMaterialBibliografico(tesis.getDoi());
            
            // Inserta en la tabla hija (Tesis)
            insertarTesisEnTabla(tesis);

            

            // Confirma la transacción
            connection.commit();

            JOptionPane.showMessageDialog(null, "Tesis insertada correctamente.");
        } catch (SQLException e) {
            // Si hay un error, realiza un rollback
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Error al insertar tesis: " + e.getMessage());
            } catch (SQLException rollbackException) {
                JOptionPane.showMessageDialog(null,"Error al realizar rollback: " + rollbackException.getMessage());
            }
        } finally {
            // Restaura el modo de autocommit
            try {
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                JOptionPane.showMessageDialog(null,"Error al restaurar el modo de autocommit: " + autoCommitException.getMessage());
            }
        }
    }
    
    public void insertarLibroEnTabla(Libro libro) throws SQLException {
        String query = "INSERT INTO Libro (ISBN, Titulo, Autor, Provedor) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Establece los parámetros en la consulta
            statement.setString(1, libro.getIsbn());
            statement.setString(2, libro.getTitulo());
            statement.setString(3, libro.getAutor());
            statement.setString(4, libro.getProvedor());

            // Ejecuta la inserción
            statement.executeUpdate();
        }
    }

    public void insertarTesisEnTabla(Tesis tesis) throws SQLException {
        String query = "INSERT INTO Tesis (Doi, Titulo, Autor, Provedor) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Establece los parámetros en la consulta
            statement.setString(1, tesis.getDoi());
            statement.setString(2, tesis.getTitulo());
            statement.setString(3, tesis.getAutor());
            statement.setString(4, tesis.getProvedor());

            // Ejecuta la inserción
            statement.executeUpdate();
        }
    }

    public void insertarMaterialBibliografico(String id) throws SQLException {
        String query = "INSERT INTO Material_Bibliografico (Id) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Establece el parámetro en la consulta
            statement.setString(1, id);

            // Ejecuta la inserción
            statement.executeUpdate();
        }
    }
    
    public void modificarLibro(String isbn, String titulo, String autor, String proveedor) {
        try {
            // Inicia la transacción
            connection.setAutoCommit(false);

            // Modifica primero en la tabla hija (Libro)
            modificarLibroEnTabla(isbn, titulo, autor, proveedor);

            // Luego modifica en la tabla padre (Material_Bibliografico) si es necesario
            // (en este ejemplo, no se modifica nada en Material_Bibliografico)

            // Confirma la transacción
            connection.commit();

            JOptionPane.showMessageDialog(null, "Libro modificado correctamente: " + isbn);
        } catch (SQLException e) {
            // Si hay un error, realiza un rollback
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Error al modificar libro: " + e.getMessage());
            } catch (SQLException rollbackException) {
                JOptionPane.showMessageDialog(null, "Error al realizar rollback: " + rollbackException.getMessage());
            }
        } finally {
            // Restaura el modo de autocommit
            try {
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                JOptionPane.showMessageDialog(null,"Error al restaurar el modo de autocommit: " + autoCommitException.getMessage());
            }
        }
    }

    public void modificarTesis(String doi, String titulo, String autor, String proveedor) {
        try {
            // Inicia la transacción
            connection.setAutoCommit(false);

            // Modifica primero en la tabla hija (Tesis)
            modificarTesisEnTabla(doi, titulo, autor, proveedor);

            // Luego modifica en la tabla padre (Material_Bibliografico) si es necesario
            // (en este ejemplo, no se modifica nada en Material_Bibliografico)

            // Confirma la transacción
            connection.commit();

            JOptionPane.showMessageDialog(null, "Tesis modificada correctamente: " + doi);
        } catch (SQLException e) {
            // Si hay un error, realiza un rollback
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Error al modificar tesis: " + e.getMessage());
            } catch (SQLException rollbackException) {
                JOptionPane.showMessageDialog(null, "Error al realizar rollback: " + rollbackException.getMessage());
            }
        } finally {
            // Restaura el modo de autocommit
            try {
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                JOptionPane.showMessageDialog(null, "Error al restaurar el modo de autocommit: " + autoCommitException.getMessage());
            }
        }
    }

    private void modificarLibroEnTabla(String isbn, String titulo, String autor, String proveedor) throws SQLException {
        String query = "UPDATE Libro SET Titulo = ?, Autor = ?, Provedor = ? WHERE ISBN = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Establece los parámetros en la consulta
            statement.setString(1, titulo);
            statement.setString(2, autor);
            statement.setString(3, proveedor);
            statement.setString(4, isbn);

            // Ejecuta la actualización
            statement.executeUpdate();
        }
    }

    private void modificarTesisEnTabla(String doi, String titulo, String autor, String proveedor) throws SQLException {
        String query = "UPDATE Tesis SET Titulo = ?, Autor = ?, Provedor = ? WHERE Doi = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Establece los parámetros en la consulta
            statement.setString(1, titulo);
            statement.setString(2, autor);
            statement.setString(3, proveedor);
            statement.setString(4, doi);

            // Ejecuta la actualización
            statement.executeUpdate();
        }
    }
    
    public Libro obtenerLibroPorISBN(String isbn) {
        Libro libro = null;

        String query = "SELECT * FROM Libro WHERE ISBN = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, isbn);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titulo = resultSet.getString("Titulo");
                String autor = resultSet.getString("Autor");
                String proveedor = resultSet.getString("Provedor");

                libro = new Libro(isbn, titulo, autor, proveedor);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener libro por ISBN: " + e.getMessage());
        }

        return libro;
    }

    public Tesis obtenerTesisPorDOI(String doi) {
        Tesis tesis = null;

        String query = "SELECT * FROM Tesis WHERE Doi = ?";

        try {
            getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, doi);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titulo = resultSet.getString("Titulo");
                String autor = resultSet.getString("Autor");
                String proveedor = resultSet.getString("Provedor");

                tesis = new Tesis(doi, titulo, autor, proveedor);
            }

            // Cierra los recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener tesis por DOI: " + e.getMessage());
        }

        return tesis;
    }

}
