/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package west.mapeos;

/**
 *
 * @author wilson
 */
public class Comentario {
    private int idcomentario;
    private String descripcion;
    private String nombre;

    public Comentario() {
    }

    public Comentario(int idcomentario, String descripcion, String nombre) {
        this.idcomentario = idcomentario;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public int getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
