/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Bibliotecaria;
import Modelo.Conexion;
import Modelo.ConexionBibliotecaria;
import Modelo.ConexionProvedor;
import Modelo.ConexionUsuario;
import Modelo.Provedores;
import Modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author orepep
 */
public class Controlador {
    Conexion conexion;
    ConexionUsuario conexionUsuario;
    ConexionProvedor conexionProvedor;
    ConexionBibliotecaria conexionBibliotecaria;
    
    public Controlador() {
        conexion = new Conexion();
        conexion.getConexion();
        conexionUsuario = new ConexionUsuario();
        conexionProvedor = new ConexionProvedor();
        conexionBibliotecaria = new ConexionBibliotecaria();
    }
    public void CerrarConexion(){
        conexion.cerrarConexion();
    }
    
    public ArrayList<Usuario> GetUsuarios(){
        ArrayList<Usuario> listaUsuarios = conexionUsuario.obtenerTodosUsuarios();
        return listaUsuarios;
    }
    
    public ArrayList<Provedores> GetProvedores(){
        ArrayList<Provedores> listaProvedores = conexionProvedor.obtenerTodosProvedores();
        return listaProvedores;
    }
    
    public ArrayList<Bibliotecaria> GetBibliotecaria(){
        ArrayList<Bibliotecaria> listaBibliotecarias = conexionBibliotecaria.obtenerTodasBibliotecarias();
        return listaBibliotecarias;
    }
    
    public Usuario getUsuarioPorDni(String Dni){
        Usuario usuario = conexionUsuario.obtenerUsuarioPorDni(Dni);
        return usuario;
    }
    
    public Provedores getProvedorPorId(String id){
        Provedores provedor = conexionProvedor.obtenerProvedoresPorId(id);
        return provedor;
    }
    
    public Bibliotecaria GetBibliotecariaPorDni(String Dni){
        Bibliotecaria bibliotecaria = conexionBibliotecaria.obtenerBibliotecariasPorDni(Dni);
        return bibliotecaria;
    }
    
    public void EliminarUsuarioPorId(String Id){
        conexionUsuario.eliminarUsuarioPorId(Id);
    }
    
    public void EliminarProvedorPorId(String id){
        conexionProvedor.eliminarProvedorPorId(id);
    }
    
    public void EliminarBibliotecariaPorDni(String Dni){
        conexionBibliotecaria.eliminarBibliotecariaPorId(Dni);
    }
    
    public void Crearcliente(String DniCliente , String nombreCliente , String TelefonoCliente , String EdadCliente) {
        Usuario usuario = new Usuario(DniCliente, nombreCliente, TelefonoCliente, EdadCliente);
        boolean crearClienteValido = ComprobarCamposCliente(usuario);

        if (crearClienteValido) {
            conexionUsuario.insertarUsuario(usuario);
        } else {
            System.out.println("Error al crear el cliente. Campos inválidos.");
        }
    }
    
    public void CrearProvedor(String id){
        Provedores provedor = new Provedores(id);
        boolean crearProvedorValido = validarDNI(id);
        
        if(crearProvedorValido){
            conexionProvedor.insertarProvedor(provedor);
        }else{
            System.out.println("Error al crear Provedor, campos inválidos");
        }
    }
    
    public void CrearBibliotecaria(String Dni , String nombre){
        Bibliotecaria b = new Bibliotecaria(Dni, nombre);
        
        if((validarDNI(Dni) == true) && (validarNombre(nombre) == true)){
            conexionBibliotecaria.insertarBibliotecaria(b);
        }else{
            System.out.println("Error al crear Bibliotecaria, campos inválidos");
        }
    }
    
    public void ModificarCliente(String DniCliente , String nombreCliente , String TelefonoCliente , String EdadCliente){
        Usuario usuario = new Usuario(DniCliente, nombreCliente, TelefonoCliente, EdadCliente);
        boolean crearClienteValido = ComprobarCamposCliente(usuario);
        
        if(crearClienteValido){
            conexionUsuario.ModificarCliente(DniCliente , nombreCliente , TelefonoCliente ,  EdadCliente);
        }else{
            System.out.println("Error al Modificar el cliente. Campos inválidos.");
        }
    }
    
    public void ModificarProvedor(String id, String id2){
        boolean crearProvedorValido = validarDNI(id2);
        
        if(crearProvedorValido){
            conexionProvedor.ModificarProvedor(id , id2);
        }else{
            System.out.println("Error al Modificar Provedor, campos inválidos");
        }
    }
    
    public void ModificarBibliotecaria(String Dni , String nombre){
        
        if((validarDNI(Dni) == true) && (validarNombre(nombre) == true)){
            conexionBibliotecaria.ModificarBibliotecaria(Dni,nombre);
        }else{
            System.out.println("Error al Modificar Bibliotecaria, campos inválidos");
        }
    }
    
    private boolean ComprobarCamposCliente(Usuario usuario) {
        return validarDNI(usuario.getDni()) &&
               validarNombre(usuario.getNombre()) &&
               validarTelefono(usuario.getTelefono()) &&
               validarEdad(usuario.getEdad());
    }

    private boolean validarDNI(String dni) {
        // Verificar que el DNI tiene 8 números seguidos de una letra
        return dni.matches("\\d{8}[a-zA-Z]");
    }

    private boolean validarNombre(String nombre) {
        // Verificar que el nombre es bastante real y no tiene caracteres raros
        return nombre.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚüÜ]+( [a-zA-ZñÑáéíóúÁÉÍÓÚüÜ]+)*");
    }

    private boolean validarTelefono(String telefono) {
        // Verificar que el teléfono tiene 8 números
        return telefono.matches("\\d{9}");
    }

    private boolean validarEdad(String edad) {
        try {
            // Convertir la cadena de edad a un número entero
            int edadNum = Integer.parseInt(edad);
            // Verificar que la edad está en el rango de 18 a 100
            return edadNum >= 18 && edadNum <= 100;
        } catch (NumberFormatException e) {
            // Si ocurre una excepción al convertir a número, la edad no es válida
            return false;
        }
    }

}