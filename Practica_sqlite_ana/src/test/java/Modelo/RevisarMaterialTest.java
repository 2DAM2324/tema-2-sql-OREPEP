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
public class RevisarMaterialTest {
    
    private static Controlador controlador;
    public RevisarMaterialTest() {
    }
    
    @BeforeAll
    public static void PrepararBasesDatosRevision() {
        controlador = new Controlador();
        
        String IdProvedor = "07020102y";
        controlador.CrearProvedor(IdProvedor);
        
        String isbn = "00425094q";
        String titulo = "Adrian el furioso";
        String autor = "Adrian";
        controlador.CrearLibro(isbn, autor, titulo, IdProvedor);
        
        String b = "80320192y";
        controlador.CrearBibliotecaria(b);
        
        
        String isbn2 = "00025094q";
        controlador.CrearLibro(isbn2, autor, titulo, IdProvedor);
        
        String b2 = "80020192y";
        controlador.CrearBibliotecaria(b2);
        
        controlador.CrearRevision(b2, isbn2);
    }
    
    @Test
    public void CrearRevision(){
        String b = "80320192y";
        String isbn = "00425094q";
        controlador.CrearRevision(b, isbn);
    }
    
    @Test 
    public void EliminarRevision(){
        String b = "80020192y";
        String isbn = "00025094q";
        controlador.EliminarRevisionPorId(ObtenerId());
    }
    
    private static String ObtenerId(){
        String id = null;
        String b = "80020192y";
        String isbn = "00025094q";
        ArrayList<RevisarMaterial> lista = controlador.GetRevision();
       
        for(RevisarMaterial r : lista){
            System.out.println("1Bibliotecaria = " + r.getBibliotecaria() + "   isbn = " + r.getMaterialBibliografico());
            if((b.equals(r.getBibliotecaria())) && (isbn.equals(r.getMaterialBibliografico()))){
                id = r.getCodigoRevision();
            }
        }
        System.out.println("id = " + id );
        return id;
    }
    
    private static String ObtenerId2(){
        String id = null;
        String b = "80320192y";
        String isbn = "00425094q";
        ArrayList<RevisarMaterial> lista = controlador.GetRevision();
       
        for(RevisarMaterial r : lista){
            System.out.println("2Bibliotecaria = " + r.getBibliotecaria() + "   isbn = " + r.getMaterialBibliografico());
            if((b.equals(r.getBibliotecaria())) && (isbn.equals(r.getMaterialBibliografico()))){
                id = r.getCodigoRevision();
            }
        }
        System.out.println("id = " + id );
        return id;
    }
    
    @AfterAll
    public static void LimpiarBaseDatosRevision() {
        controlador.EliminarRevisionPorId(ObtenerId2());
        controlador.EliminarLibroPorIsbn("00425094q");
        controlador.EliminarLibroPorIsbn("00025094q");
        controlador.EliminarProvedorPorId("07020102y");
        controlador.EliminarBibliotecariaPorDni("80320192y");
        controlador.EliminarBibliotecariaPorDni("80020192y");
    } 
}
