package west;

public enum TipoUsuario {
    ADMINISTRADOR("Administrador", 1), USUARIO("Usuario", 2), DESCONOCIDO("Desconocido", 3);

    private String nombre;
    private int id;

    private TipoUsuario(String nombre, int puesto) {
        this.nombre = nombre;
        this.id = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
    
    public static TipoUsuario intAtipo(int op){
        TipoUsuario resp;
        switch (op) {
            case 1:
                resp= TipoUsuario.ADMINISTRADOR;
                break;
            case 2:
                resp= TipoUsuario.USUARIO;
                break;
            default:
                resp=TipoUsuario.DESCONOCIDO;
        }
        return resp;
    }
}
