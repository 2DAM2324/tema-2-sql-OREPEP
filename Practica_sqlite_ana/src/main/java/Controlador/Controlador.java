/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Bibliotecaria;
import Modelo.Conexion;
import Modelo.ConexionBibliotecaria;
import Modelo.ConexionMaterialBibliografico;
import Modelo.ConexionPrestamo;
import Modelo.ConexionProvedor;
import Modelo.ConexionRevision;
import Modelo.ConexionUsuario;
import Modelo.Libro;
import Modelo.Prestamo;
import Modelo.Provedores;
import Modelo.RevisarMaterial;
import Modelo.Tesis;
import Modelo.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author orepep
 */
public class Controlador {
    Conexion conexion;
    ConexionUsuario conexionUsuario;
    ConexionProvedor conexionProvedor;
    ConexionBibliotecaria conexionBibliotecaria;
    ConexionMaterialBibliografico conexionMaterialBibliografico;
    ConexionRevision  conexionRevision;
    ConexionPrestamo conexionPrestamo;
    
    public Controlador() {
        conexion = new Conexion();
        conexion.getConexion();
        conexionUsuario = new ConexionUsuario();
        conexionProvedor = new ConexionProvedor();
        conexionBibliotecaria = new ConexionBibliotecaria();
        conexionMaterialBibliografico = new ConexionMaterialBibliografico();
        conexionRevision = new ConexionRevision();
        conexionPrestamo = new ConexionPrestamo();
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
    
    public ArrayList<Libro> GetLibros(){
        ArrayList<Libro> lista = conexionMaterialBibliografico.obtenerTodosLibros();
        return lista;
    }
    
    public ArrayList<Tesis> GetTesis(){
        ArrayList<Tesis> lista = conexionMaterialBibliografico.obtenerTodasTesis();
        return lista;
    }
    
    public ArrayList<RevisarMaterial> GetRevision(){
        ArrayList<RevisarMaterial> lista = conexionRevision.obtenerTodasRevisiones();
        return lista;
    }
    
    public ArrayList<Prestamo> GetPrestamo(){
        ArrayList<Prestamo> lista = conexionPrestamo.obtenerTodosPrestamos();
        return lista;
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
    
    public Libro GetLibroPorIsbn(String isbn){
        Libro libro = conexionMaterialBibliografico.obtenerLibroPorISBN(isbn);
        return libro;
    }
    
    public Tesis GetTesisPorDoi(String doi){
        Tesis tesis = conexionMaterialBibliografico.obtenerTesisPorDOI(doi);
        return tesis;
    }
    
    public RevisarMaterial GetRevisionPorId(int id){
        RevisarMaterial revisar = conexionRevision.obtenerRevisionPorId(id);
        return revisar;
    }
    
    public Prestamo GetPrestamoPorId(int id){
        Prestamo prestamo = conexionPrestamo.obtenerPrestamoPorId(id);
        return prestamo;
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
    
    public void EliminarLibroPorIsbn(String isbn){
        conexionMaterialBibliografico.eliminarLibroPorISBN(isbn);
    }
    
    public void EliminarTesisPorDoi(String doi){
        conexionMaterialBibliografico.eliminarTesisPorDOI(doi);
    }
    
    public void EliminarRevisionPorId(String id){
        int idRevision = Integer.parseInt(id);
        conexionRevision.eliminarRevisionPorId(idRevision);
    }
    
    public void EliminarPrestamoPorId(String id){
        int idPrestamo = Integer.parseInt(id);
        conexionPrestamo.eliminarPrestamoPorId(idPrestamo);
    }
    
    public void Crearcliente(String DniCliente , String nombreCliente , String TelefonoCliente , String EdadCliente) {
        Usuario usuario = new Usuario(DniCliente, nombreCliente, TelefonoCliente, EdadCliente);
        boolean crearClienteValido = ComprobarCamposCliente(usuario);

        if (crearClienteValido) {
            conexionUsuario.insertarUsuario(usuario);
        } else {
            JOptionPane.showMessageDialog(null, "Error al crear el cliente. Campos inválidos.");
        }
    }
    
    public void CrearProvedor(String id){
        Provedores provedor = new Provedores(id);
        boolean crearProvedorValido = validarDNI(id);
        
        if(crearProvedorValido){
            conexionProvedor.insertarProvedor(provedor);
        }else{
            JOptionPane.showMessageDialog(null, "Error al crear Provedor, campos inválidos");
        }
    }
    
    public void CrearBibliotecaria(String Dni ){
        Bibliotecaria b = new Bibliotecaria(Dni);
        
        if((validarDNI(Dni) == true)){
            conexionBibliotecaria.insertarBibliotecaria(b);
        }else{
            JOptionPane.showMessageDialog(null, "Error al crear Bibliotecaria, campos inválidos");
        }
    }
    
    public void CrearLibro(String isbn , String autor , String nombre , String provedor){
        Libro libro = new Libro(isbn, autor, nombre, provedor);
        
        if((validarIsbn(isbn) == true) && (validarNombre(nombre) == true) && (validarNombre(autor) == true) && (validarDNI(provedor) == true) ){
            conexionMaterialBibliografico.insertarLibro(libro);
        }else{
            JOptionPane.showMessageDialog(null, "Error al crear Libro, campos inválidos");
        }
    }
    
    public void CrearTesis(String doi , String autor , String nombre , String provedor){
        Tesis tesis = new Tesis(doi, autor, nombre, provedor);
        
        if((validarIsbn(doi) == true) && (validarNombre(nombre) == true) && (validarNombre(autor) == true) && (validarDNI(provedor) == true) ){
            conexionMaterialBibliografico.insertarTesis(tesis);
        }else{
            JOptionPane.showMessageDialog(null, "Error al crear tesiss, campos inválidos");
        }
    }
    
    public void CrearRevision(String Bibliotecaria , String MaterialBibliografico){
        RevisarMaterial revisarMaterial = new RevisarMaterial(Bibliotecaria, MaterialBibliografico);
        
        if((validarDNI(Bibliotecaria) == true) && (validarIsbn(MaterialBibliografico) == true)){
            conexionRevision.insertarRevision(revisarMaterial);
        }else{
            JOptionPane.showMessageDialog(null, "Error al crear Revisión , campos inválidos");
        }
    }
    
    public void CrearPrestamo(String usuario , String MaterialBibliografico){
        Prestamo prestamo = new Prestamo(usuario, MaterialBibliografico);
        
        if((validarDNI(usuario) == true) && (validarIsbn(MaterialBibliografico) == true)){
            conexionPrestamo.insertarPrestamo(prestamo);
        }else{
            JOptionPane.showMessageDialog(null, "Error al crear Prestamo , campos inválidos");
        }
    }
    
    public void ModificarCliente(String DniCliente , String nombreCliente , String TelefonoCliente , String EdadCliente){
        Usuario usuario = new Usuario(DniCliente, nombreCliente, TelefonoCliente, EdadCliente);
        boolean crearClienteValido = ComprobarCamposCliente(usuario);
        
        if(crearClienteValido){
            conexionUsuario.ModificarCliente(DniCliente , nombreCliente , TelefonoCliente ,  EdadCliente);
        }else{
            JOptionPane.showMessageDialog(null, "Error al Modificar el cliente. Campos inválidos.");
        }
    }
    
    public void ModificarProvedor(String id, String id2){
        boolean crearProvedorValido = validarDNI(id2);
        
        if(crearProvedorValido){
            conexionProvedor.ModificarProvedor(id , id2);
        }else{
            JOptionPane.showMessageDialog(null, "Error al Modificar Provedor, campos inválidos");
        }
    }
    
    public void ModificarBibliotecaria(String Dni , String nombre){
        //System.out.println(Dni + "     " + nombre);
        if((validarDNI(nombre) == true)){
            conexionBibliotecaria.ModificarBibliotecaria(Dni,nombre);
        }else{
            JOptionPane.showMessageDialog(null, "Error al Modificar Bibliotecaria1, campos inválidos");
        }
    }
    
    public void ModificarLibro(String isbn , String autor , String nombre , String provedor ){
        
        if((validarIsbn(isbn) == true) && (validarNombre(nombre) == true) && (validarNombre(autor) == true) && (validarDNI(provedor) == true) ){
            conexionMaterialBibliografico.modificarLibro(isbn,autor, nombre ,provedor);
        }else{
            JOptionPane.showMessageDialog(null, "Error al Modificar Libro, campos inválidos");
        }
    }
    
    public void ModificarTesis(String doi , String autor , String nombre , String provedor){
        if((validarIsbn(doi) == true) && (validarNombre(nombre) == true) && (validarNombre(autor) == true) && (validarDNI(provedor) == true) ){
            conexionMaterialBibliografico.modificarTesis(doi,autor, nombre ,provedor);
        }else{
            JOptionPane.showMessageDialog(null, "Error al Modificar tesis, campos inválidos");
        }
    }
    
    public void ModificarRevision(int id , String Bibliotecaria , String MaterialBibliografico){
        if((validarDNI(Bibliotecaria) == true) && (validarIsbn(MaterialBibliografico) == true)){
            conexionRevision.modificarRevision(id, Bibliotecaria, MaterialBibliografico);
        }else{
            JOptionPane.showMessageDialog(null, "Error al Modificar Revisión , campos inválidos");
        }
    }
    
    public void ModificarPrestamo(int id , String usuario , String MaterialBibliografico){
        if((validarDNI(usuario) == true) && (validarIsbn(MaterialBibliografico) == true)){
            conexionPrestamo.modificarPrestamo(id, usuario , MaterialBibliografico);
        }else{
            JOptionPane.showMessageDialog(null,"Error al Modificar Préstamo , campos inválidos");
        }
    }
    
    private boolean ComprobarCamposCliente(Usuario usuario) {
        return validarDNI(usuario.getDni()) &&
               validarNombre(usuario.getNombre()) &&
               validarTelefono(usuario.getTelefono()) &&
               validarEdad(usuario.getEdad());
    }
    
    
    private boolean validarIsbn(String dni) {
        // Verificar que el DNI tiene 8 números seguidos de una letra
        return dni.matches("\\d{8}[a-zA-Z]");
    }
    
     public static boolean validarDNI(String dni) {
        // Verificar la longitud del DNI
        if (dni.length() != 9) {
            return false;
        }

        // Extraer el número y la letra de control
        String numeroStr = dni.substring(0, 8);
        char letraControl = dni.charAt(8);

        try {
            // Convertir el número a entero
            int numero = Integer.parseInt(numeroStr);

            // Calcular la letra de control esperada
            char letraCalculada = calcularLetraControl(numero);

            // Comparar la letra de control esperada con la proporcionada
            return letraControl == letraCalculada;
        } catch (NumberFormatException e) {
            // Error al convertir el número
            return false;
        }
    }

    private static char calcularLetraControl(int numero) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

        // Calcular el índice de la letra de control
        int indice = numero % 23;

        // Obtener la letra de control correspondiente
        return letras.charAt(indice);
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
