package west.ws;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import reportes.Fun;
import reportes.mapeos.RecaudacionSubcategoria;
import west.mapeos.Categoria;
import west.mapeos.Comentario;
import west.mapeos.DenunciaEnIni;
import west.mapeos.Iniciativa;
import west.mapeos.Patrocinio;
import west.mapeos.Recompensa;
import west.mapeos.Subcategoria;
import west.mapeos.Usuario;
import west.modulos.Funciones;

@WebService(serviceName = "pruebaServicio")
public class pruebaServicio {

    @WebMethod(operationName = "validarPersonaWS")
    public Usuario validarPersonaWS(@WebParam(name = "nick") String nick, @WebParam(name = "pass") String pass) {
        Usuario resp;
        resp = Funciones.verificarUsuarioBD(nick, pass);
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "crearUsuarioWS")
    public Boolean crearUsuarioWS(@WebParam(name = "usu") Usuario usu) {
        boolean resp = false;

        try {
            resp = Funciones.guardarUsuario(usu);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion crearUsuarioWS: " + e);
        }
        return resp;
    }

    @WebMethod(operationName = "lsUsuariosWS")
    public List<Usuario> lsUsuariosWS() {
        List<Usuario> resp = null;

        try {
            resp = Funciones.lsUsuarios();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsUsuariosWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUsuario")
    public Usuario getUsuarioWS(@WebParam(name = "id") String id) {
        //TODO write your implementation code here:
        Usuario resp = null;
        try {
            resp = Funciones.getUsuario(id);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion getUsuarioWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "modificarUsuarioWS")
    public boolean modificarUsuarioWS(@WebParam(name = "usu") Usuario usu) {
        //TODO write your implementation code here:
        boolean resp = false;
        try {
            resp = Funciones.modificarUsuario(usu);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsCategoriaWS: " + e);
        }
        return resp;
    }

    @WebMethod(operationName = "lsCategoriaWS")
    public List<Categoria> lsCategoriaWS() {
        List<Categoria> lsCategoria = null;
        try {
            lsCategoria = Funciones.lsCategoria();
        } catch (SQLException ex) {
            Logger.getLogger(pruebaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsCategoria;
    }

    @WebMethod(operationName = "queryWS")
    public Integer queryWS(@WebParam(name = "query") String query) {

        int resp = -1;
        try {
            resp = Funciones.query(query);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion queryWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "lsSubcategoriaWS")
    public List<Subcategoria> lsSubcategoriaWS(String idCategoria) {
        //TODO write your implementation code here:
        List<Subcategoria> resp = null;
        try {
            resp = Funciones.lsSubcategoria(idCategoria);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsSubcategoriaWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "lsIniciativaWS")
    public List<Iniciativa> lsIniciativaWS(String modo) {
        //TODO write your implementation code here:
        List<Iniciativa> resp = null;
        try {
            resp = Funciones.lsIniciativa(modo);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsIniciativaWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getIniciativaWS")
    public Iniciativa getIniciativaWS(@WebParam(name = "idIniciativa") String idIniciativa) {
        //TODO write your implementation code here:
        Iniciativa resp = null;
        try {
            resp = Funciones.getIniciativa(idIniciativa);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion getIniciativaWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "lsRecompensaWS")
    public List<Recompensa> lsRecompensaWS(@WebParam(name = "idUsuOIni") String idUsuOIni, @WebParam(name = "modo") String modo) {
        List<Recompensa> resp = null;
        try {
            resp = Funciones.lsRecompensa(idUsuOIni, modo);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsRecompensaWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "existeCategoriaWS")
    public Integer existeCategoriaWS(@WebParam(name = "str") String str) {
        int resp = -1;
        try {
            resp = Funciones.existeCategoria(str);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion existeCategoriaWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "existeSubcategoriaWS")
    public int existeSubcategoriaWS(@WebParam(name = "str") String str) {
        int resp = -1;
        try {
            resp = Funciones.existeSubcategoria(str);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion existeSubcategoriaWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "lsComentarioWS")
    public List<Comentario> lsComentarioWS(@WebParam(name = "idIniciativa") String idIniciativa) {
        List<Comentario> resp = null;
        try {
            resp = Funciones.lsComentario(idIniciativa);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsComentarioWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "lsDenunciaEnIniWS")
    public List<DenunciaEnIni> lsDenunciaEnIniWS() {
        List<DenunciaEnIni> resp = null;
        try {
            resp = Funciones.lsDenunciaEnIni();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsDenunciaEnIniWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "lsPatrocinioWS")
    public List<Patrocinio> lsPatrocinioWS(@WebParam(name = "idusuOidrecomOtodo") String idusuOidrecomOtodo, @WebParam(name = "modo") String modo) {
        List<Patrocinio> resp = null;
        try {
            resp = Funciones.lsPatrocinio(idusuOidrecomOtodo, modo);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsPatrocinioWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "recaudacionWS")
    public Double recaudacionWS(@WebParam(name = "idIniciativa") String idIniciativa) {
        double resp = -1;
        try {
            resp = Funciones.recaudacion(idIniciativa);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion recaudacionWS: " + e);
        }
        return resp;
    }

    @WebMethod(operationName = "backersWS")
    public Integer backersWS(@WebParam(name = "idIniciativa") String idIniciativa) {
        int resp = -1;
        try {
            resp = Funciones.backers(idIniciativa);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion backersWS: " + e);
        }
        return resp;
    }

    @WebMethod(operationName = "lsIniciativaDenunciadaWS")
    public List<Iniciativa> lsIniciativaDenunciadaWS() {
        List<Iniciativa> resp = null;
        try {
            resp = Funciones.lsIniciativaDenunciada();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsIniciativaDenunciadaWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "lsTodasSubcategoriasWS")
    public List<Subcategoria> lsTodasSubcategoriasWS() {
        List<Subcategoria> resp = null;
        try {
            resp = Funciones.lsSubcategoria();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsTodasSubcategoriasWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "lsRecaduacionSubcategoriaWS")
    public List<String> lsRecaduacionSubcategoriaWS(@WebParam(name = "op") String op) {
        List<String> resp = null;
        try {
            resp = Fun.lsRecaduacionSubcategoria(op);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsRecaduacionSubcategoriaWS: " + e);
        }
        return resp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "reporteCategoriaWS")
    public List<String> reporteCategoriaWS(@WebParam(name = "op") String op) {
        List<String> resp = null;
        try {
            resp = Fun.reporteCategoria(op);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion lsRecaduacionSubcategoriaWS: " + e);
        }
        return resp;
    }
    
    @WebMethod(operationName = "recomComprada")
    public List<String> recomComprada() {
        List<String> resp = null;
        try {
            resp = Fun.recomComprada();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion recomComprada: " + e);
        }
        return resp;
    }

    @WebMethod(operationName = "recomCompradaIni")
    public List<String> recomCompradaIni() {
        List<String> resp = null;
        try {
            resp = Fun.recomCompradaIni();
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion recomCompradaIni: " + e);
        }
        return resp;
    }
    
    @WebMethod(operationName = "topWS")
    public List<String> topWS(@WebParam(name = "modo") String modo) {
        List<String> resp = null;
        try {
            resp = Fun.top(modo);
        } catch (Exception e) {
            System.err.println("Ocurrio una excepcion recomCompradaIni: " + e);
        }
        return resp;
    }
}
