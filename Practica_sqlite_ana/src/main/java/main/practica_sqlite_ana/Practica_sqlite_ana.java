/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main.practica_sqlite_ana;

import Controlador.Controlador;
import Ventana.Ventana1;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author orepep
 */
public class Practica_sqlite_ana {

    public static void main(String[] args) {
        Ventana1 ventana = null;
        try{
            ventana = new Ventana1();
            ventana.setVisible(true);
        }catch(IOException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }catch(SAXException ex){
            ex.printStackTrace();
        }
    }
}
