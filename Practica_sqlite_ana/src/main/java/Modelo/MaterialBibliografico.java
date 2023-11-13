/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.ArrayList;

/**
 *
 * @author orepep
 */
public class MaterialBibliografico {
    protected String titulo;
    protected String autor;
    protected Provedores provedor;

    public MaterialBibliografico(String titulo, String autor , Provedores provedor) {
        this.titulo = titulo;
        this.autor = autor;
        this.provedor = provedor;//comprobar si el = funciona bien . 
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}


