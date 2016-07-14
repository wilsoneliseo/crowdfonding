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
public class DenunciaEnIni {
    int idcomentario;
    String descripcion;
    String nombre;
    String nombreIni;

    public DenunciaEnIni() {
    }

    public DenunciaEnIni(int idcomentario, String descripcion, String nombre, String nombreIni) {
        this.idcomentario = idcomentario;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.nombreIni = nombreIni;
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

    public String getNombreIni() {
        return nombreIni;
    }

    public void setNombreIni(String nombreIni) {
        this.nombreIni = nombreIni;
    }
    
    
}
