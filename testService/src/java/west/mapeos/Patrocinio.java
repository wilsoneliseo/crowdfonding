package west.mapeos;

public class Patrocinio {
    int idpatrocinio;
    String ruta;
    String nombre;
    String descripcion;
    String nombreIni;

    public Patrocinio() {
    }

    public Patrocinio(int idpatrocinio, String ruta, String nombre, String descripcion, String nombreIni) {
        this.idpatrocinio = idpatrocinio;
        this.ruta=ruta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreIni = nombreIni;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getIdpatrocinio() {
        return idpatrocinio;
    }

    public void setIdpatrocinio(int idpatrocinio) {
        this.idpatrocinio = idpatrocinio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreIni() {
        return nombreIni;
    }

    public void setNombreIni(String nombreIni) {
        this.nombreIni = nombreIni;
    }
    
     
}
