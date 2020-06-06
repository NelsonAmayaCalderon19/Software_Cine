/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import servicios.conexion;

/**
 *
 * @author USUARIO
 */
public class Cine {
private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String SQL="";
    private PeliculaVO pelicula;
    private float precio;
    private AsientoVO[][] asientos;

    ArrayList<EspectadorVO> espectadores;
    ArrayList<PeliculaVO> peliculas;

    public Cine() {
    }

    public String obtenerUbicacionAsiento(int i, int j) {
        return asientos[i][j].getLetra() + asientos[i][j].getNumero();
    }

    public boolean asientoVacio(int i ,int j){
        return asientos[i][j].getOcupado()==null ?true :false;
    }
    
    public String obtenerInformacionEspectadorDeAsiento(int i ,int j){
        return asientos[i][j].getOcupado().informacion();
    }
    public void iniciarSimulacion(int filas, int columnas) {
        Random rr = new Random();
        pelicula = peliculas.get(rr.nextInt(peliculas.size()));
        precio = 5000;
        asientos = new AsientoVO[filas][columnas];
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                char letra = (char) (j + 65);
                asientos[i][j] = new AsientoVO(letra + "", filas - i + "", null);
            }
        }
        int casientos = filas * columnas;
        int cpersonas = espectadores.size();
int aux=0;
        for (int i = 0; i < espectadores.size(); i++) {
            EspectadorVO a = espectadores.get(i);
            if (a.getDinero() >= precio && a.getEdad() >= pelicula.getEdadMinima()) {                
                Random r = new Random();
                int f = r.nextInt(filas);
                int c = r.nextInt(columnas);
                if (asientos[f][c].getOcupado() == null) {
                    aux++;
                System.out.println("aux "+aux +" persona "+ a.informacion());
                    asientos[f][c].setOcupado(a);
                    cpersonas--;
                    casientos--;

                } else if (cpersonas != 0 && casientos != 0) {
                    i--;
                }
            }else{
                cpersonas--;
            }
        }
    }

    public String informacionPersonas() {
        String informacion = "";
        for (int i = 0; i < this.espectadores.size(); i++) {
            informacion += espectadores.get(i).informacion() + "\n";
        }
        return informacion;
    }

    public String informacionPeliculas() {
        String informacion = "";
        for (int i = 0; i < this.peliculas.size(); i++) {
            informacion += peliculas.get(i).informacion() + "\n";
        }
        return informacion;
    }

public void Cine(String pelicula) {
        int capacidad=0;
        espectadores = new ArrayList<>();
        peliculas = new ArrayList<>();
   String SQL = "SELECT * FROM ESPECTADOR WHERE pelicula='"+pelicula+"'";
    String SQL2="select count(id) as capacidad from espectador";
        try{
     Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(SQL2);
        while(rs.next()){
       capacidad =rs.getInt("capacidad");       
        }
        }catch(Exception e){           
            }       
    for (int i = 0; i < 1; i++) {            
            try{
                 Statement st2 = cn.createStatement();
        ResultSet rs2=st2.executeQuery(SQL);
        while(rs2.next()){
            String titulo = rs2.getString("nombre");
int edad = rs2.getInt("edad");
Double dinero = rs2.getDouble("dinero");
            this.espectadores.add(new EspectadorVO(titulo,edad,dinero));
        }}catch(Exception e){           
            }
        }
    int capacidad2=0;
String SQL5 = "SELECT * FROM PELICULA";
    String SQL3="select count(id) as capac from pelicula";
        try{
     Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(SQL3);
        while(rs.next()){
       capacidad2 =rs.getInt("capac");       
        }
        }catch(Exception e){           
            }
           for (int i = 0; i < 1; i++) {
         try{
                 Statement st2 = cn.createStatement();
        ResultSet rs2=st2.executeQuery(SQL5);
        while(rs2.next()){
     String titulo = rs2.getString("titulo");
String duracion= rs2.getString("duracion");
int edad = rs2.getInt("edadminima");
String director = rs2.getString("director");
            this.peliculas.add(new PeliculaVO(titulo,duracion,edad,director));
        }
    }catch(Exception e){           
            }
           }
}
}
