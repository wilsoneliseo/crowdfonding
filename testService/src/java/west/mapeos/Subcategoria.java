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
public class Subcategoria {
    int idsubcategoria;
    String nombreSub;
    Categoria categoria;

    public Subcategoria() {
    }

    public Subcategoria(int idsubcategoria, String nombreSub, Categoria categoria) {
        this.idsubcategoria = idsubcategoria;
        this.nombreSub = nombreSub;
        this.categoria = categoria;
    }
    
    public Subcategoria(int idsubcategoria, String nombreSub, int categoria) {
        this.idsubcategoria = idsubcategoria;
        this.nombreSub = nombreSub;
        this.categoria = new Categoria(categoria, "sin nombre");
    }    

    public int getIdsubcategoria() {
        return idsubcategoria;
    }

    public void setIdsubcategoria(int idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public String getNombreSub() {
        return nombreSub;
    }

    public void setNombreSub(String nombreSub) {
        this.nombreSub = nombreSub;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
