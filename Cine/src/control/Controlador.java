/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import servicios.conexion;
import vista.*;
import modelVO.*;

public class Controlador implements ActionListener,Runnable  {
private Cartelera c = null;
private Registro_Pelicula f = null;
private Registro_Espectador r = null;
private  List<JButton> labels;
 private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String SQL="";
    JButton boton = new JButton();
    Thread h1;
        Thread h2;  
    public Cine cine;
    public PanelAsientos asientos;
    public MostrarInfo mostrar;
    EspectadorVO es = new EspectadorVO();
    public Controlador(Cartelera c, Registro_Pelicula f,Registro_Espectador r,PanelAsientos asientos) {
        super();
        this.c=c;
        this.f=f;
        this.r=r;
        this.asientos=asientos;
        c.setVisible(true);
        f.setVisible(false);
        r.setVisible(false);
        this.ActionListener(this);
        labels = new ArrayList<>();
        VerCartelera();
        Reloj();
        cine=new Cine();
    }
     public void Reloj() {
        h1 = new Thread(this);
        h1.start();       
    }
     
public void VerCartelera(){
    c.cbxpelis.removeAllItems();
    int con=0;
c.panel.removeAll();
        c.setVisible(true);
        String SQL ="select * from PELICULA";
        try{
        Statement st2 = cn.createStatement();
        ResultSet rs2=st2.executeQuery(SQL);
        while(rs2.next()){         
String sCadena = rs2.getString(7);
String sSubCadena = sCadena.substring(0,2);
String sSubCadenas = sCadena.substring(3,5);
String sCadena2 = c.jLabel3.getText();
String sSubCadena2 = sCadena2.substring(0,2);
String sSubCadenas2 = sCadena2.substring(3,5);
int num1= Integer.parseInt(sSubCadena+sSubCadenas);
int num2= Integer.parseInt(sSubCadena2+sSubCadenas2);
if(num1>num2)
{
     c.cbxpelis.addItem(rs2.getString(2));
    con++; 
        boton = new JButton("<html><p><html>Titulo de la Pelicula:  "+rs2.getString(2)+"<p>"+"Sinopsis: "+rs2.getString(4)+"<p>"+"\nDuración: "+rs2.getString(3)+"<p>"+"\nEdad Minima: "+rs2.getString(5)+"<p>"+"\nDirector: "+rs2.getString(6)+"<p>"+"\nHora Función: "+rs2.getString(7)+"<p>"+"\nValor Entrada: "+rs2.getString(8)+"");
       boton.addActionListener(this);
        boton.setForeground(Color.black);
        if(con%2==0){
        boton.setBackground(Color.cyan);
        }else if(con%2!=0){
        boton.setBackground(Color.white);
        }
        boton.setFont(new Font("Arial ",Font.BOLD,15));
            c.panel.add(boton);
            labels.add(boton);
            c.panel.updateUI();
            c.setVisible(true);      
        ByteArrayOutputStream ouput = new ByteArrayOutputStream();
        InputStream datos=rs2.getBinaryStream(9);
        int tem=datos.read();
        while(tem>=0){
            ouput.write((char)tem);
            tem=datos.read();
        }
        Image image = Toolkit.getDefaultToolkit().createImage(ouput.toByteArray());
        image=image.getScaledInstance(200, 200, 1);
        boton.setIcon(new ImageIcon(image));
       boton.setHorizontalTextPosition( SwingConstants.CENTER );
boton.setVerticalTextPosition( SwingConstants.BOTTOM );
}
        }
        }catch(Exception ev){
        JOptionPane.showMessageDialog(null, ev.getMessage());
        }
}
    private void ActionListener(Controlador a) {
    f.btnRegistrar.addActionListener(a);
    c.btnAceptar.addActionListener(a);
    c.btnVolver.addActionListener(a);
    r.btnComprar.addActionListener(a);
    c.btnIniciarSimulacion.addActionListener(a);
    c.btnFinalizar.addActionListener(a);
    }    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
  if(e.getSource()==f.btnRegistrar){
    if (f.t1.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar El Titulo de la Pelicula");
            f.t1.requestFocus();
            return;
        }
        if (f.t2.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar La Duración de la Pelicula");
            f.t2.requestFocus();
            return;
        }

        if (f.t3.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes Dar una Breve Descripcion de la Pelicula");
            f.t3.requestFocus();
            return;
        }

        if (f.t4.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar la edad minima para ver la Pelicula");
            f.t4.requestFocus();
            return;
        }
        if (f.t5.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Nombre del Director de la Pelicula");
            f.t5.requestFocus();
            return;
        }if(f.validar_CampoHora(f.tHora.getText())==false){
        JOptionPane.showConfirmDialog(null, "Formato de Hora Incorrecto");
            f.tHora.requestFocus();
            return;
        }
         if (f.t6.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar el Valor de la Entrada de esta Pelicula");
            f.t6.requestFocus();
            return;
        }
        FileInputStream archivoFoto;
        File nombreFoto;
        SQL ="INSERT INTO PELICULA (titulo,duracion,descripcion,edadminima,director,horaFuncion,precioEntrada,imagen)"+
        "VALUES (?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst = cn.prepareStatement(SQL);
            nombreFoto = new File(f.tDir.getText());
            archivoFoto = new FileInputStream(nombreFoto);
            pst.setString(1, f.t1.getText());
            pst.setString(2, f.t2.getText());
            pst.setString(3, f.t3.getText());
            pst.setString(4, f.t4.getText());
            pst.setString(5, f.t5.getText());
            pst.setString(6, f.tHora.getText());
            pst.setDouble(7, Double.parseDouble(f.t6.getText()));
            pst.setBinaryStream(8, archivoFoto,archivoFoto.available());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"Pelicula Registrada Exitosamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception ev){
            JOptionPane.showConfirmDialog(null, ev.getMessage());
        }       
f.setVisible(false);
VerCartelera();
c.setVisible(true);
  }if(e.getSource()==c.btnAceptar){
int seleccionado = c.cbxpelis.getSelectedIndex();
      String pelicula = c.cbxpelis.getItemAt(seleccionado);
      if(c.cbxpelis.getItemCount() == 0){
      JOptionPane.showMessageDialog(null, "¡Advertencia! Ojo no Peliculas Disponibles");
      return;
      }
      r.t4.setText((String) c.cbxpelis.getItemAt(seleccionado));
      r.setVisible(true);
      c.setVisible(false);
  }if(e.getSource()==c.btnVolver){
  f.setVisible(true);
       c.setVisible(false);
  }if(e.getSource()==r.btnComprar){   
       if (r.t1.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar El Nombre del Espectador");
            r.t1.requestFocus();
            return;
        }
        if (r.t2.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar La Edad del Espectador");
            r.t2.requestFocus();
            return;
        }
        if (r.t3.getText().length() == 0) {
            JOptionPane.showConfirmDialog(null, "Debes ingresar la Cantidad de Dinero que posee el Espectador");
            r.t3.requestFocus();
            return;
        }
        if(ValidarEdad(r.t4.getText())==false){
        JOptionPane.showMessageDialog(null, "No Cumple con la Edad Minima Requerida \npara Ingresar a la Sala");
        r.setVisible(false);
        c.setVisible(true);
        }else if(ValidarDinero(r.t4.getText())==false){
        JOptionPane.showMessageDialog(null, "No Posee la Cantidad de Dinero Suficiente \npara Cancelar en Valor del Tiquet");
        r.setVisible(false);
        c.setVisible(true);
        }
        else if(ValidarCapacidad(r.t4.getText())==false){
        JOptionPane.showMessageDialog(null, "Lo Sentimos No hay Tiquets para esta Pelicula");
        r.setVisible(false);
        c.setVisible(true);    
        }
        else{
            Double precio=0.0;
            SQL = "SELECT * FROM PELICULA WHERE titulo='"+r.t4.getText()+"'";   
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(SQL);       
        while(rs.next()){
               precio = rs.getDouble("precioEntrada");
        }     
  SQL ="INSERT INTO ESPECTADOR (nombre,edad,dinero,pelicula)"+
        "VALUES (?,?,?,?)"; 
        try{
            PreparedStatement pst = cn.prepareStatement(SQL);           
            pst.setString(1, r.t1.getText());
            pst.setInt(2,Integer.parseInt(r.t2.getText()));
            pst.setDouble(3, precio);
            pst.setString(4, r.t4.getText());
            if(pst.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"Venta de Tiquet Exitoso","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                if(precio!=Double.parseDouble(r.t3.getText())){
                JOptionPane.showMessageDialog(null, "Le Sobran: "+(Double.parseDouble(r.t3.getText())-precio+" Pesos"));
                }
            }
        }catch(Exception ev){
            JOptionPane.showConfirmDialog(null, ev.getMessage());
        }
        r.setVisible(false);
        c.setVisible(true);
  }
        r.t1.setText("");
        r.t2.setText("");
        r.t3.setText("");
  }if(e.getSource()==c.btnIniciarSimulacion){
      Simulacion();           
   }if(e.getSource()==c.btnFinalizar){
       JOptionPane.showMessageDialog(null, totalizar());              
    } 
        }catch(Exception ev){
        JOptionPane.showMessageDialog(null, ev.getMessage());
        }       
    }
    public String totalizar(){
         String total="";    
     String SQL4= "select sum(Dinero) as totalDia, count(id) as cantidadEspectadores from espectador";
try{
        Statement st3 = cn.createStatement();
        ResultSet rs3=st3.executeQuery(SQL4);
while(rs3.next()){
total = "El Total de Espectadores fue "+rs3.getString("cantidadEspectadores")+" y el Total Recaudado "+rs3.getString("totalDia");
        }
    }catch(Exception e){
      JOptionPane.showConfirmDialog(null, e);     
    }
return total;
     }
    public void run() {
        Thread ct = Thread.currentThread();          
        while(ct==h1){
            if(r.isShowing() || f.isShowing() || asientos.isShowing()){
            }else{
        VerCartelera();
        Simulacion();
            }       
        try{
        Thread.sleep(60000);
        }catch(InterruptedException e){
        }        
        }
     }
 
  public boolean ValidarCapacidad(String cantidad){
      int cont=0;
   String SQL3="select count(id) as capac from pelicula WHERE titulo='"+cantidad+"'";
  try{
  Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(SQL3);       
        while(rs.next()){
            if(rs.getInt("capac") <= 72){
                cont++;
            }            
        } 
      if(cont!=0){
              cont=0;
              return true;              
          }else{
              cont=0;
              return false;           
          } 
  }catch(Exception e){
  JOptionPane.showConfirmDialog(null, e);
      cont=0;
       return false; 
  }  
  }
    public boolean ValidarEdad(String buscar){
        int cont=0;
    SQL = "SELECT * FROM PELICULA WHERE titulo='"+buscar+"'";
    try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(SQL);       
        while(rs.next()){
            if(rs.getInt("edadminima") < Integer.parseInt(r.t2.getText())){
                cont++;
            }            
        }          
          if(cont!=0){
              cont=0;
              return true;              
          }else{
              cont=0;
              return false;           
          }           
    }catch(Exception e){
      JOptionPane.showConfirmDialog(null, e);
      cont=0;
       return false;      
    }
}
     public boolean ValidarDinero(String buscar){
        int cont=0;
    SQL = "SELECT * FROM PELICULA WHERE titulo='"+buscar+"'";
    try{
        Statement st = cn.createStatement();
        ResultSet rs=st.executeQuery(SQL);       
        while(rs.next()){
            if(rs.getInt("precioEntrada") < Integer.parseInt(r.t3.getText())){
                cont++;
            }           
        }          
          if(cont!=0){
              cont=0;
              return true;              
          }else{
              cont=0;
              return false;           
          }      
    }catch(Exception e){
      JOptionPane.showConfirmDialog(null, e);
      cont=0;
       return false;      
    }
}   
    private void darAccionAsientos(JButton boton,int i ,int j){
        mostrar=new MostrarInfo();
        boton.addActionListener(new ActionListener() {              
            @Override           
            public void actionPerformed(ActionEvent e) { 
                mostrar.dispose();               
               mostrar.setVisible(false);
               mostrar.getTxtMostrar().setText( cine.obtenerInformacionEspectadorDeAsiento(i, j));
               String sSQL ="UPDATE ESPECTADOR SET silla=? "+
            " WHERE nombre=?";   
    try{
        PreparedStatement pst = cn.prepareStatement(sSQL);          
          pst.setString(1, cine.obtenerUbicacionAsiento(i, j));         
          pst.setString(2, cine.obtenerInformacionEspectadorDeAsiento(i, j));
          int n=pst.executeUpdate();
          if(n!=0){
          }else{
          }
    }catch(Exception ev){
        JOptionPane.showConfirmDialog(null, ev.getMessage());
    }
                }           
        });
    }
   
    public boolean Simulacion(){ 
        String titulo="";
        boolean estado=false;
String SQL ="select * from PELICULA";
        try{
        Statement st2 = cn.createStatement();
        ResultSet rs2=st2.executeQuery(SQL);
        while(rs2.next() ){         
String sCadena = rs2.getString(7);
String sSubCadena = sCadena.substring(0,2);
String sSubCadenas = sCadena.substring(3,5);
String sCadena2 = c.jLabel3.getText();
String sSubCadena2 = sCadena2.substring(0,2);
String sSubCadenas2 = sCadena2.substring(3,5);
int num3= Integer.parseInt(sSubCadenas);
int num4= Integer.parseInt(sSubCadenas2);
int num5= Integer.parseInt(sSubCadena);
int num6= Integer.parseInt(sSubCadena2);
int minutos = num5*3600;
int minutos2 = num6*3600;
int total=minutos+num3;
int total2 =minutos2+num4;
if(total2==total){
    titulo=rs2.getString(2);
    JOptionPane.showMessageDialog(null, "Atención esta a punto de Iniciar la Pelicula: "+rs2.getString(2));
estado=true;
}
        }
        } catch (SQLException ex) {
          Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
      }
      try{
          if(estado==true){
              cine.Cine(titulo);
  int filas=Integer.valueOf(8);
                    int columnas = Integer.valueOf(9);
                    cine.iniciarSimulacion(filas,columnas);
                    asientos=new PanelAsientos(filas, columnas);
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            asientos.botones[i][j].setText(cine.obtenerUbicacionAsiento(i, j));
                            if (cine.asientoVacio(i, j)) {
                                asientos.botones[i][j].setBackground(Color.GREEN);
                            }else{
                                asientos.botones[i][j].setBackground(Color.RED);
                                darAccionAsientos(asientos.botones[i][j], i, j);
                                asientos.botones[i][j].doClick();
                            }
                        }                      
                    }
                     asientos.setExtendedState(JFrame.MAXIMIZED_BOTH);
                     asientos.setVisible(true);                     
         }
                } catch (Exception ex) {
                }  
      return estado;
                 }               
}


   
    
   

