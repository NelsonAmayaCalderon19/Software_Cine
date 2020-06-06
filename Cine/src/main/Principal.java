/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import vista.*;
import control.*;
/**
 *
 * @author TURBO Core i3
 */
public class Principal {
    public static void main(String[] args) {
        
    
   Principal p = new Principal();
   Cartelera c = new Cartelera();
   Registro_Pelicula f = new Registro_Pelicula();
   Registro_Espectador r = new Registro_Espectador();
   PanelAsientos pr = new PanelAsientos();
   Controlador con = new Controlador(c,f,r,pr);
    }
}
