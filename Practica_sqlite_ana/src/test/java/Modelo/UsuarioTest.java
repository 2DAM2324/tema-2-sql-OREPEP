package Modelo;

import Controlador.Controlador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private static Controlador controlador;

    @BeforeAll
    public static void PrepararBaseDeDatos() {
        controlador = new Controlador();

        // Datos de ejemplo para el usuario
        String dniCliente = "25378501A";
        String nombreCliente = "John Doe";
        String telefonoCliente = "123456789";
        String edadCliente = "25";
        
        // Crea un usuario para las pruebas
        controlador.Crearcliente(dniCliente, nombreCliente, telefonoCliente, edadCliente);
    }

    @Test
    public void testCrearUsuario() {
        // Datos de ejemplo para el usuario
        String dniCliente = "13425894P";
        String nombreCliente = "Jane Doe";
        String telefonoCliente = "987654321";
        String edadCliente = "30";

        // Intenta crear un usuario
        controlador.Crearcliente(dniCliente, nombreCliente, telefonoCliente, edadCliente);

    }
    //t.me/inter_ferencias
    @Test
    public void testEliminarUsuario() {
        // Datos de ejemplo para el usuario
        String dniCliente = "25378501A";

        // Verifica que el usuario existe antes de eliminarlo
        assertTrue(controlador.GetUsuarios().stream().anyMatch(u -> u.getDni().equals(dniCliente)));

        // Elimina el usuario
        controlador.EliminarUsuarioPorId(dniCliente);

        // Verifica que el usuario fue eliminado
        assertTrue(controlador.GetUsuarios().stream().noneMatch(u -> u.getDni().equals(dniCliente)));
    }
    
    @Test
    public void ModificarUsuario(){
        String dniCliente = "13425894P";
        String nombreCliente = "Jane ÑOp";
        String telefonoCliente = "007654321";
        String edadCliente = "20";
        
        // Intenta crear un usuario
        controlador.ModificarCliente(dniCliente, nombreCliente, telefonoCliente, edadCliente);
    }
    
    @AfterAll
    public static void LimpiarBaseDatos(){
        controlador.EliminarUsuarioPorId("13425894P");
    }
}
