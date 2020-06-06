/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelVO;

import java.awt.Image;
import java.sql.Time;

/**
 *
 * @author TURBO Core i3
 */
public class PeliculaVO {
    public Integer id;
    public String titulo;
    public String duracion;
    public String descripcion;
    public Integer edadMinima;
    public String director;
    public Time horaFuncion;
    public Double precioEntrada;
    public Image imagen;

    public PeliculaVO() {
    }

    public PeliculaVO(Integer id, String titulo, String duracion, String descripcion, Integer edadMinima, String director, Time horaFuncion, Double precioEntrada, Image imagen) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.edadMinima = edadMinima;
        this.director = director;
        this.horaFuncion = horaFuncion;
        this.precioEntrada = precioEntrada;
        this.imagen = imagen;
    }
 public PeliculaVO(String titulo, String duracion, Integer edad, String director) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.edadMinima = edad;
        this.director = director;
    }
    
    public String informacion(){
        return "Titulo : "+titulo +" Duracion : "+duracion +"minutos Edad : "+edadMinima+" Director : "+director;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Time getHoraFuncion() {
        return horaFuncion;
    }

    public void setHoraFuncion(Time horaFuncion) {
        this.horaFuncion = horaFuncion;
    }

    public Double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(Double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
   
    
}
