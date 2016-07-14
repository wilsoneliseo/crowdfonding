/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package west.mapeos;

public class Recompensa {

    private int idrecompensa;
    private String descripcion;
    private double p_minimo;
    private int cantidad ;
    private String nombreIni;
    private String nombre;

    public Recompensa() {
    }

    public Recompensa(int idrecompensa, String descripcion, double p_minimo, int cantidad, String nombreIni, String nombre) {
        this.idrecompensa = idrecompensa;
        this.descripcion = descripcion;
        this.p_minimo = p_minimo;
        this.cantidad = cantidad;
        this.nombreIni = nombreIni;
        this.nombre = nombre;
    }

    public int getIdrecompensa() {
        return idrecompensa;
    }

    public void setIdrecompensa(int idrecompensa) {
        this.idrecompensa = idrecompensa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getP_minimo() {
        return p_minimo;
    }

    public void setP_minimo(double p_minimo) {
        this.p_minimo = p_minimo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreIni() {
        return nombreIni;
    }

    public void setNombreIni(String nombreIni) {
        this.nombreIni = nombreIni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
