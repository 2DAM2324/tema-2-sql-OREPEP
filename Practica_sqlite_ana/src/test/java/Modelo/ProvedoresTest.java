/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import Controlador.Controlador;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author orepep
 */
public class ProvedoresTest {
    
    private static Controlador controlador;
    
    public ProvedoresTest() {
    }
    
    @BeforeAll
    public static void PrepararBaseDeDatosProvedor(){
        controlador = new Controlador();
        
        String IdProvedor = "87320102P";
        
        //crear Provedor para las pruebas
        controlador.CrearProvedor(IdProvedor);
    }
    
    @Test
    public void testCrearProvedor(){
        String IdProvedor = "00000000P";
        
        //crear Provedor para las pruebas
        controlador.CrearProvedor(IdProvedor);
    }
    
    @Test
    public void testEliminarProvedor(){
        String IdProvedor = "87320102P";
        // Verifica que el usuario existe antes de eliminarlo
        assertTrue(controlador.GetProvedores().stream().anyMatch(u -> u.getCodProvedor().equals(IdProvedor)));

        // Elimina el usuario
        controlador.EliminarProvedorPorId(IdProvedor);

        // Verifica que el usuario fue eliminado
        assertTrue(controlador.GetProvedores().stream().noneMatch(u -> u.getCodProvedor().equals(IdProvedor)));
    }
    
    @Test 
    public void ModificarProvedor(){
        String IdProvedor = "13425894P";
        String IdP = "00000000P";
        
        controlador.ModificarProvedor(IdP, IdProvedor);
    }
    
    @AfterAll
    public static void LimpiarBAseDatosProvedor(){
        controlador.EliminarProvedorPorId("13425894P");
    }
    
}
