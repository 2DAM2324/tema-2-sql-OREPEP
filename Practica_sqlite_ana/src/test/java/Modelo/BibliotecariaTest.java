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
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author orepep
 */
public class BibliotecariaTest {
    
    private static Controlador controlador;
    
    public BibliotecariaTest() {
    }
    
     @BeforeAll
    public static void PrepararBaseDeDatosBibliotecaria(){
        controlador = new Controlador();
        
        String b = "74648043W";
        
        //crear bibliotecaria para las pruebas
        controlador.CrearBibliotecaria(b);
    }
    
    @Test
    public void LeerBibliotecaria(){
        ArrayList<Bibliotecaria> lista = controlador.GetBibliotecaria();
    }
    
    @Test
    public void testCrearProvedor(){
        String b = "75931747F";
        
        //crear bibliotecaria para las pruebas
        controlador.CrearBibliotecaria(b);
    }
    
    @Test
    public void testEliminarBibliotecaria(){
        String bibliotecaria = "74648043W";
        // Verifica que el usuario existe antes de eliminarlo
        assertTrue(controlador.GetBibliotecaria().stream().anyMatch(u -> u.getDni().equals(bibliotecaria)));

        // Elimina el bibliotecaria
        controlador.EliminarBibliotecariaPorDni(bibliotecaria);

        // Verifica que la bibliotecaria fue eliminada
        assertTrue(controlador.GetBibliotecaria().stream().noneMatch(u -> u.getDni().equals(bibliotecaria)));
    }
    
    @Test 
    public void ModificarBibliotecaria(){
        String dniBibliotecaria = "78265971F";
        String Dnib = "75931747F";
        
        controlador.ModificarBibliotecaria(Dnib, dniBibliotecaria);
    }
    
    @AfterAll
    public static void LimpiarBAseDatosdniBibliotecaria(){
        controlador.EliminarBibliotecariaPorDni("78265971F");
    }
    
}
