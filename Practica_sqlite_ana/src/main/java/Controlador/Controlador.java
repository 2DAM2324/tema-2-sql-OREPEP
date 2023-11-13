/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Conexion;
import Modelo.ConexionUsuario;
import Modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author orepep
 */
public class Controlador {
    Conexion conexion;
    ConexionUsuario conexionUsuario;
    
    public Controlador() {
        conexion = new Conexion();
        conexion.getConexion();
        conexionUsuario = new ConexionUsuario();
    }
    public void CerrarConexion(){
        conexion.cerrarConexion();
    }
    
    public ArrayList<Usuario> GetUsuarios(){
        ArrayList<Usuario> listaUsuarios = conexionUsuario.obtenerTodosUsuarios();
        return listaUsuarios;
    }
    
    public void EliminarUsuarioPorId(String Id){
        conexionUsuario.eliminarUsuarioPorId(Id);
    }
    
    public void Crearcliente(){
        
    }
}
