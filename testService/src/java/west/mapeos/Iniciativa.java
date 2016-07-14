/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package west.mapeos;

public class Iniciativa {
    int idiniciativa;
    String nombreIni;
    Double metaeconomica;
    String metatiempo;
    Usuario usuario;
    Subcategoria subcategoria;
    int estado;

    public Iniciativa(int idiniciativa, String nombreIni, Double metaeconomica, String metatiempo, Usuario usuario, Subcategoria subcategoria, int estado) {
        this.idiniciativa = idiniciativa;
        this.nombreIni = nombreIni;
        this.metaeconomica = metaeconomica;
        this.metatiempo = metatiempo;
        this.usuario = usuario;
        this.subcategoria = subcategoria;
        this.estado = estado;
    }

    public Iniciativa() {
    }

    public int getIdiniciativa() {
        return idiniciativa;
    }

    public void setIdiniciativa(int idiniciativa) {
        this.idiniciativa = idiniciativa;
    }

    public String getNombreIni() {
        return nombreIni;
    }

    public void setNombreIni(String nombreIni) {
        this.nombreIni = nombreIni;
    }

    public Double getMetaeconomica() {
        return metaeconomica;
    }

    public void setMetaeconomica(Double metaeconomica) {
        this.metaeconomica = metaeconomica;
    }

    public String getMetatiempo() {
        return metatiempo;
    }

    public void setMetatiempo(String metatiempo) {
        this.metatiempo=invertirFecha(metatiempo);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    private String invertirFecha(String yyyymmdd){
        String partes[]=yyyymmdd.split("-");
        return partes[2]+"-"+partes[1]+"-"+partes[0];
    }
    
}
