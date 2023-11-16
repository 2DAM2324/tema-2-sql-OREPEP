/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import Controlador.Controlador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
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
        
        String b = "87320102P";
        
        //crear bibliotecaria para las pruebas
        controlador.CrearBibliotecaria(b);
    }
    
    @Test
    public void testCrearProvedor(){
        String b = "00000000P";
        
        //crear bibliotecaria para las pruebas
        controlador.CrearBibliotecaria(b);
    }
    
    @Test
    public void testEliminarBibliotecaria(){
        String bibliotecaria = "87320102P";
        // Verifica que el usuario existe antes de eliminarlo
        assertTrue(controlador.GetBibliotecaria().stream().anyMatch(u -> u.getDni().equals(bibliotecaria)));

        // Elimina el bibliotecaria
        controlador.EliminarBibliotecariaPorDni(bibliotecaria);

        // Verifica que la bibliotecaria fue eliminada
        assertTrue(controlador.GetBibliotecaria().stream().noneMatch(u -> u.getDni().equals(bibliotecaria)));
    }
    
    @Test 
    public void ModificarBibliotecaria(){
        String dniBibliotecaria = "13425894P";
        String Dnib = "00000000P";
        
        controlador.ModificarBibliotecaria(Dnib, dniBibliotecaria);
    }
    
    @AfterAll
    public static void LimpiarBAseDatosdniBibliotecaria(){
        controlador.EliminarBibliotecariaPorDni("13425894P");
    }
    
}
