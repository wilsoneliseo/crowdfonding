package west.mapeos;

public class Categoria {

    private int idcategoria;
    private String nombreCat;

    public Categoria() {
    }

    public Categoria(int idcategoria, String nombreCat) {
        this.idcategoria = idcategoria;
        this.nombreCat = nombreCat;
    }
    
    
    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

}
