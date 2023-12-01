/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import Controlador.Controlador;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author orepep
 */
public class PrestamoTest {
    
    private static Controlador controlador;
    
    public PrestamoTest() {

    }
    
    @BeforeAll
    public static void setUpClass() {
    controlador = new Controlador();
        String dniCliente = "75931747F";
        String nombreCliente = "John Doe";
        String telefonoCliente = "123456789";
        String edadCliente = "25";
        // Crea un usuario para las pruebas
        controlador.Crearcliente(dniCliente, nombreCliente, telefonoCliente, edadCliente);
        
        String isbn = "13425094q";
        String titulo = "Adrian el furioso";
        String autor = "Adrian";
        
        String IdProvedor = "75931747F";
        controlador.CrearProvedor(IdProvedor);
        controlador.CrearLibro(isbn, autor, titulo, IdProvedor);
        controlador.CrearLibro("13420004q", autor, titulo, IdProvedor);
        controlador.CrearPrestamo(dniCliente, isbn);
        
    }
    
    @Test
    public void LeerPrestamos(){
        ArrayList<Prestamo> lista = controlador.GetPrestamo();
    }
    
    @Test
    public void CrearPrestamo(){
        
        String dniCliente = "74648043W";
        String nombreCliente = "John Doe";
        String telefonoCliente = "123456789";
        String edadCliente = "25";
        // Crea un usuario para las pruebas
        controlador.Crearcliente(dniCliente, nombreCliente, telefonoCliente, edadCliente);
        
        String isbn = "13420094q";
        String titulo = "Adrian el furioso";
        String autor = "Adrian";
        
        String IdProvedor = "74648043W";
        controlador.CrearProvedor(IdProvedor);
        controlador.CrearLibro(isbn, autor, titulo, IdProvedor);

        controlador.CrearPrestamo(dniCliente, isbn);
        
    }
    
    @Test
    public void EliminarPrestamo(){
        controlador.EliminarPrestamoPorId(ObtenerId());
    }
    
    @Test
    public void testModificarPrestamo() {
        // Supongamos que tu controlador está disponible como una instancia llamada 'controlador'
        /*
        // Datos de prueba
        String dniCliente = "75931747F";
        String isbn = "13420004q";

        // Llamada al método que quieres probar
        String idString = ObtenerId(); // Supongamos que ObtenerId() devuelve un String
        int id = Integer.parseInt(idString);
        controlador.ModificarPrestamo(id, dniCliente, isbn);*/

    }
    
    @AfterAll
    public static void tearDownClass() {
        controlador.EliminarPrestamoPorId(ObtenerId2());
        controlador.EliminarUsuarioPorId("75931747F");
        controlador.EliminarUsuarioPorId("74648043W");
        controlador.EliminarLibroPorIsbn("13425094q");
        controlador.EliminarLibroPorIsbn("13420094q");
        controlador.EliminarLibroPorIsbn("13420004q");
        controlador.EliminarProvedorPorId("74648043W");
        controlador.EliminarProvedorPorId("75931747F");
    }
    
    private static String ObtenerId(){
        String id = null;
        String b = "75931747F";
        String isbn = "13425094q";
        ArrayList<Prestamo> lista = controlador.GetPrestamo();
       
        for(Prestamo r : lista){
            if((b.equals(r.getUsuario())) && (isbn.equals(r.getMaterialBibliografico()))){
                id = r.getId();
            }
        }
        return id;
    }
    
    private static String ObtenerId2(){
        String id = null;
        String b = "74648043W";
        String isbn = "13420094q";
        ArrayList<Prestamo> lista = controlador.GetPrestamo();
       
        for(Prestamo r : lista){
            if((b.equals(r.getUsuario())) && (isbn.equals(r.getMaterialBibliografico()))){
                id = r.getId();
            }
        }
        return id;
    }
    
}
