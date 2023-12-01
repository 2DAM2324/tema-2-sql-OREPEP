/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import Controlador.Controlador;
import java.util.ArrayList;
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
        
        String IdProvedor = "74648043W";
        
        //crear Provedor para las pruebas
        controlador.CrearProvedor(IdProvedor);
        String IdProvedor2 = "76592763A";
        
        //crear Provedor para las pruebas
        controlador.CrearProvedor(IdProvedor2);
    }
    
    @Test
    public void LeerProvedores(){
        ArrayList<Provedores> lista = controlador.GetProvedores();
    }
    
    @Test
    public void testCrearProvedor(){
        String IdProvedor = "75931747F";
        
        //crear Provedor para las pruebas
        controlador.CrearProvedor(IdProvedor);
    }
    
    @Test
    public void testEliminarProvedor(){
        String IdProvedor = "74648043W";
        
        assertTrue(controlador.GetProvedores().stream().anyMatch(u -> u.getCodProvedor().equals(IdProvedor)));

        controlador.EliminarProvedorPorId(IdProvedor);

        assertTrue(controlador.GetProvedores().stream().noneMatch(u -> u.getCodProvedor().equals(IdProvedor)));
    }
    
    @Test 
    public void ModificarProvedor(){
        String IdProvedor = "78265971F";
        String IdP = "76592763A";
        
        controlador.ModificarProvedor(IdP, IdProvedor);
    }
    
    @AfterAll
    public static void LimpiarBAseDatosProvedor(){
        controlador.EliminarProvedorPorId("78265971F");
        controlador.EliminarProvedorPorId("75931747F");
    }
    
}
