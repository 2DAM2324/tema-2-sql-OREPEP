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
public class MaterialBibliograficoTest {
    
    private static Controlador controlador;
    
    public MaterialBibliograficoTest() {
    }
    
    @BeforeAll
    public static void PrepararBasesDatosMaterialBiblliografico() {
        controlador = new Controlador();
        
        String Doi = "25308501A";
        String isbn = "13425094q";
        String titulo = "Adrian el furioso";
        String autor = "Adrian";
        
        String IdProvedor = "75931747F";
        controlador.CrearProvedor(IdProvedor);
        controlador.CrearLibro(isbn, autor, titulo, IdProvedor);
        controlador.CrearTesis(Doi, autor, titulo, IdProvedor);
    }
    
    @Test
    public void LeerMaterialBibliografico(){
        ArrayList<Libro> libros = controlador.GetLibros();
        ArrayList<Tesis> Tesis = controlador.GetTesis();
    }
    
    @Test 
    public void CrearLibro(){
        String isbn = "13005094q";
        String titulo = "Adrian el furioso";
        String autor = "Adrian";
        String IdProvedor = "75931747F";
        controlador.CrearLibro(isbn, autor, titulo, IdProvedor);
    }
    
    @Test 
    public void EliminarLibro(){
        controlador.EliminarLibroPorIsbn("13425094q");
    }
    
    @Test
    public void ModificarLibro(){
        String isbn = "13425094q";
        String titulo = "Adrian el furioso";
        String autor = "Yo";
        String IdProvedor = "75931747F";
        controlador.ModificarLibro(isbn, autor, titulo, IdProvedor);
    }
    
    @Test
    public void CrearTesis(){
        String doi = "13005094w";
        String titulo = "Adrian el furioso";
        String autor = "Adrian";
        String IdProvedor = "75931747F";
        
        controlador.CrearTesis(doi, autor, titulo, IdProvedor);
    }
    
    @Test
    public void EliminarTesis(){
        
        controlador.EliminarTesisPorDoi("25308501A");
    }
    
    @Test
    public void ModificarTEsis(){
        String doi = "25308501A";
        String titulo = "Adrian el furioso";
        String autor = "Yo";
        String IdProvedor = "75931747F";
        controlador.ModificarTesis(doi, autor, titulo, IdProvedor);
    }
    
    @AfterAll
    public static void LimpiarBaseDatosMaterialBibliografico() {
        controlador.EliminarTesisPorDoi("13005094w");
        controlador.EliminarLibroPorIsbn("13005094q");
        controlador.EliminarProvedorPorId("75931747F");
    }
   
    
}
