/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package west.modulos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import west.TipoUsuario;
import west.dao.Conexion;
import west.mapeos.Categoria;
import west.mapeos.Comentario;
import west.mapeos.DenunciaEnIni;
import west.mapeos.Recompensa;
import west.mapeos.Iniciativa;
import west.mapeos.Patrocinio;
import west.mapeos.Subcategoria;
import west.mapeos.Usuario;

/**
 *
 * @author wilson
 */
public class Funciones {

    public static Usuario verificarUsuarioBD(String nick, String pass) {
        PreparedStatement ps = null;
        Connection currentCon = null;
        ResultSet rs = null;
        Usuario per = null;

        try {
            //connect to DB 
            currentCon = Conexion.getConexion();
            ps = currentCon.prepareStatement("select * from usuario where nickname=? and contrasena=?;");
            ps.setString(1, nick);
            ps.setString(2, pass);

            rs = ps.executeQuery();
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more) {
                System.out.println("Lo siento, el usuario no ha sido encontrado");
                return per;
            } //if user exists set the isValid variable to true
            else if (more) {
                //recuperar tupla mediante rs (result set)
                int idusu = rs.getInt("idUsuario");
                String nom = rs.getString("nombre");
                String dir = rs.getString("direccion");
                String tel = rs.getString("telefono");
                String cuentab = rs.getString("cuentabancaria");
                int tipo = rs.getInt("tipo");

                //llenar el objeto tipo persona con los datos de result set
                per = new Usuario();
                per.setIdusuario(idusu);
                per.setNickname(nick);
                per.setContrasena(pass);
                per.setNombre(nom);
                per.setDireccion(dir);
                per.setTelefono(tel);
                per.setCuentabancaria(cuentab);
                if (tipo == TipoUsuario.ADMINISTRADOR.getId()) {
                    per.setTipo(TipoUsuario.ADMINISTRADOR);
                }
                if (tipo == TipoUsuario.USUARIO.getId()) {
                    per.setTipo(TipoUsuario.USUARIO);
                }
            }
        } catch (Exception ex) {
            System.err.println("Fallo login: Ocurrio una excepcion! " + ex);
        } //some exception handling
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
                ps = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }

        return per;

    }

    public static boolean guardarUsuario(Usuario usu) throws SQLException {

        Connection db_conex = null;
        PreparedStatement prep_sentencia = null;

        String query
                = "INSERT INTO usuario("
                + "idusuario, nickname, contrasena, nombre, direccion, telefono, cuentabancaria, tipo)"
                + "VALUES (default, ?, ?, ?, ?, ?, ?, ?);";

        try {
            db_conex = Conexion.getConexion();
            prep_sentencia = db_conex.prepareStatement(query);

            prep_sentencia.setString(1, usu.getNickname());
            prep_sentencia.setString(2, usu.getContrasena());
            prep_sentencia.setString(3, usu.getNombre());
            prep_sentencia.setString(4, usu.getDireccion());
            prep_sentencia.setString(5, usu.getTelefono());
            prep_sentencia.setString(6, usu.getCuentabancaria());
            prep_sentencia.setInt(7, usu.getTipo().getId());

            // execute insert SQL stetement
            prep_sentencia.executeUpdate();
            db_conex.close();

            return true;

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;

        } finally {

            if (prep_sentencia != null) {
                prep_sentencia.close();
            }

            if (db_conex != null) {
                db_conex.close();
            }

        }
    }

    public static boolean modificarUsuario(Usuario usu) throws SQLException {

        Connection db_conex = null;
        PreparedStatement prep_sentencia = null;

        String query
                = "UPDATE usuario\n"
                + "SET  nickname=?, contrasena=?, nombre=?, direccion=?, \n"
                + "       telefono=?, cuentabancaria=?, tipo=?\n"
                + "WHERE idusuario=?;";

        try {
            db_conex = Conexion.getConexion();
            prep_sentencia = db_conex.prepareStatement(query);

            prep_sentencia.setString(1, usu.getNickname());
            prep_sentencia.setString(2, usu.getContrasena());
            prep_sentencia.setString(3, usu.getNombre());
            prep_sentencia.setString(4, usu.getDireccion());
            prep_sentencia.setString(5, usu.getTelefono());
            prep_sentencia.setString(6, usu.getCuentabancaria());
            prep_sentencia.setInt(7, usu.getTipo().getId());
            prep_sentencia.setInt(8, usu.getIdusuario());

            // execute insert SQL stetement
            prep_sentencia.executeUpdate();
            db_conex.close();

            return true;

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;

        } finally {

            if (prep_sentencia != null) {
                prep_sentencia.close();
            }

            if (db_conex != null) {
                db_conex.close();
            }

        }
    }

    public static List<Usuario> lsUsuarios() throws SQLException {
        Connection db_conex = null;
        PreparedStatement prep_sentencia = null;
        List<Usuario> ltsCat = new ArrayList();
        Usuario u;
        int idusuario;
        String nickname;
        String contrasena;
        String nombre;
        String direccion;
        String telefono;
        String cuentabancaria;
        int tipo;

        String query = "SELECT idusuario, nickname, contrasena, nombre, direccion, telefono, \n"
                + "       cuentabancaria, tipo\n"
                + "  FROM usuario;";

        try {
            db_conex = Conexion.getConexion();
            prep_sentencia = db_conex.prepareStatement(query);

            // execute insert SQL stetement
            ResultSet resp = prep_sentencia.executeQuery();
            while (resp.next()) {
                //recupearndo campos del result set
                idusuario = resp.getInt("idusuario");
                nickname = resp.getString("nickname");
                contrasena = resp.getString("contrasena");
                nombre = resp.getString("nombre");
                direccion = resp.getString("direccion");
                telefono = resp.getString("telefono");
                cuentabancaria = resp.getString("cuentabancaria");
                tipo = resp.getInt("tipo");

                //creando el objeto con los datos anteriores
                u = new Usuario();
                u.setIdusuario(idusuario);
                u.setNickname(nickname);
                u.setContrasena(contrasena);
                u.setNombre(nombre);
                u.setDireccion(direccion);
                u.setTelefono(telefono);
                u.setCuentabancaria(cuentabancaria);
                u.setTipo(TipoUsuario.intAtipo(tipo));

                //adicionando a la lista
                ltsCat.add(u);
            }
            db_conex.close();

            return ltsCat;

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return null;

        } finally {

            if (prep_sentencia != null) {
                prep_sentencia.close();
            }

            if (db_conex != null) {
                db_conex.close();
            }

        }
    }

    public static Usuario getUsuario(String id) throws SQLException {
        Connection db_conex = null;
        PreparedStatement prep_sentencia = null;
        Usuario u = null;

        String query = "SELECT idusuario, nickname, contrasena, nombre, direccion, telefono, \n"
                + "       cuentabancaria, tipo\n"
                + "  FROM usuario WHERE idusuario=?;";

        try {
            db_conex = Conexion.getConexion();
            prep_sentencia = db_conex.prepareStatement(query);
            prep_sentencia.setInt(1, Integer.parseInt(id));

            // execute insert SQL stetement
            ResultSet resp = prep_sentencia.executeQuery();
            while (resp.next()) {
                //recupearndo campos del result set
                int idusuario = resp.getInt("idusuario");
                String nickname = resp.getString("nickname");
                String contrasena = resp.getString("contrasena");
                String nombre = resp.getString("nombre");
                String direccion = resp.getString("direccion");
                String telefono = resp.getString("telefono");
                String cuentabancaria = resp.getString("cuentabancaria");
                int tipo = resp.getInt("tipo");

                //creando el objeto con los datos anteriores
                u = new Usuario();
                u.setIdusuario(idusuario);
                u.setNickname(nickname);
                u.setContrasena(contrasena);
                u.setNombre(nombre);
                u.setDireccion(direccion);
                u.setTelefono(telefono);
                u.setCuentabancaria(cuentabancaria);
                u.setTipo(TipoUsuario.intAtipo(tipo));

            }
            db_conex.close();

            return u;

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return null;

        } finally {

            if (prep_sentencia != null) {
                prep_sentencia.close();
            }

            if (db_conex != null) {
                db_conex.close();
            }

        }
    }


    public static List<Recompensa> lsRecompensa(String idUsuOIni, String modo) throws SQLException {
        List<Recompensa> lsResp = new ArrayList();
        ResultSet rs = null;
        if (modo.equalsIgnoreCase("porUsuario")) {
            rs = queryResultSet("SELECT r.idrecompensa, r.descripcion, r.p_minimo, r.cantidad, i.\"nombreIni\", u.nombre\n"
                    + "FROM recompensa r, iniciativa i, usuario u\n"
                    + "WHERE r.iniciativa=i.idiniciativa and i.idusuario=u.idusuario and u.idusuario<>" + idUsuOIni + ";");
            while (rs.next()) {
                //recuperando del result set
                int idrecompensa = rs.getInt("idrecompensa");
                String descripcion = rs.getString("descripcion");
                double p_minimo = rs.getDouble("p_minimo");
                int cantidad = rs.getInt("cantidad");
                String nombreIni = rs.getString("nombreIni");
                String nombre = rs.getString("nombre");
                lsResp.add(new Recompensa(idrecompensa, descripcion, p_minimo, cantidad, nombreIni, nombre));
            }
        } else if (modo.equalsIgnoreCase("porIniciativa")) {
            rs = queryResultSet("SELECT r.idrecompensa, r.descripcion, r.p_minimo, r.cantidad, i.\"nombreIni\"\n"
                    + "FROM recompensa r, iniciativa i\n"
                    + "WHERE r.iniciativa=i.idiniciativa and r.iniciativa=" + idUsuOIni + ";");
            while (rs.next()) {
                //recuperando del result set
                int idrecompensa = rs.getInt("idrecompensa");
                String descripcion = rs.getString("descripcion");
                double p_minimo = rs.getDouble("p_minimo");
                int cantidad = rs.getInt("cantidad");
                String nombreIni = rs.getString("nombreIni");
                String nombre = "sin";
                lsResp.add(new Recompensa(idrecompensa, descripcion, p_minimo, cantidad, nombreIni, nombre));
            }
        }

        return lsResp;
    }

    // el modo es para indicar si se quieren todas la iniciativas o las iniciativas de un usuario especifico
    public static List<Iniciativa> lsIniciativa(String modo) throws SQLException {
        List<Iniciativa> lsResp = new ArrayList();
        ResultSet rs = null;
        if (modo.equalsIgnoreCase("*")) {
            String todasIniciativas = "SELECT i.idiniciativa, i.\"nombreIni\", i.metaeconomica, i.metatiempo, u.idusuario, u.nombre, s.idsubcategoria, s.\"nombreSub\", c.idcategoria, c.\"nombreCat\", estado\n"
                    + "FROM iniciativa i, categoria c, subcategoria s, usuario u\n"
                    + "WHERE i.idusuario=u.idusuario AND  i.idsubcategoria=s.idsubcategoria AND s.idcategoria=c.idcategoria;";
            rs = queryResultSet(todasIniciativas);
        } else {
            String iniciativasDeUnUsuario = "SELECT i.idiniciativa, i.\"nombreIni\", i.metaeconomica, i.metatiempo, u.idusuario, u.nombre, s.idsubcategoria, s.\"nombreSub\", c.idcategoria, c.\"nombreCat\", estado\n"
                    + "FROM iniciativa i, categoria c, subcategoria s, usuario u\n"
                    + "WHERE i.idusuario=u.idusuario AND  i.idsubcategoria=s.idsubcategoria AND s.idcategoria=c.idcategoria and u.idusuario=" + modo + ";";
            rs = queryResultSet(iniciativasDeUnUsuario);
        }

        while (rs.next()) {
            //recuperando del result set
            int idiniciativa = rs.getInt("idiniciativa");
            String nombreIni = rs.getString("nombreIni");
            double metaeconomica = rs.getDouble("metaeconomica");
            String metatiempo = rs.getString("metatiempo");
            int idusuario = rs.getInt("idusuario");
            String nombre = rs.getString("nombre");
            int idsubcategoria = rs.getInt("idsubcategoria");
            String nombreSub = rs.getString("nombreSub");
            int idcategoria = rs.getInt("idcategoria");
            String nombreCat = rs.getString("nombreCat");
            int estado = rs.getInt("estado");

            //creando objeto categoria
            Categoria c = new Categoria();
            c.setIdcategoria(idcategoria);
            c.setNombreCat(nombreCat);

            //creando objecto subcategoria
            Subcategoria s = new Subcategoria();
            s.setIdsubcategoria(idsubcategoria);
            s.setNombreSub(nombreSub);
            s.setCategoria(c);

            //creando objecto usuario
            Usuario u = new Usuario();
            u.setIdusuario(idusuario);
            u.setNombre(nombre);

            lsResp.add(new Iniciativa(idiniciativa, nombreIni, metaeconomica, metatiempo, u, s, estado));
        }
        return lsResp;
    }

    public static List<Iniciativa> lsIniciativaDenunciada() throws SQLException {
        List<Iniciativa> lsResp = new ArrayList();
        ResultSet rs = null;
        String todasIniciativas = "SELECT i.idiniciativa, i.\"nombreIni\", i.metaeconomica, i.metatiempo, u.idusuario, u.nombre, s.idsubcategoria, s.\"nombreSub\", c.idcategoria, c.\"nombreCat\", estado\n"
                + "FROM iniciativa i, categoria c, subcategoria s, usuario u\n"
                + "WHERE i.idusuario=u.idusuario AND  i.idsubcategoria=s.idsubcategoria AND s.idcategoria=c.idcategoria AND estado=1;";
        rs = queryResultSet(todasIniciativas);

        while (rs.next()) {
            //recuperando del result set
            int idiniciativa = rs.getInt("idiniciativa");
            String nombreIni = rs.getString("nombreIni");
            double metaeconomica = rs.getDouble("metaeconomica");
            String metatiempo = rs.getString("metatiempo");
            int idusuario = rs.getInt("idusuario");
            String nombre = rs.getString("nombre");
            int idsubcategoria = rs.getInt("idsubcategoria");
            String nombreSub = rs.getString("nombreSub");
            int idcategoria = rs.getInt("idcategoria");
            String nombreCat = rs.getString("nombreCat");

            //creando objeto categoria
            Categoria c = new Categoria();
            c.setIdcategoria(idcategoria);
            c.setNombreCat(nombreCat);

            //creando objecto subcategoria
            Subcategoria s = new Subcategoria();
            s.setIdsubcategoria(idsubcategoria);
            s.setNombreSub(nombreSub);
            s.setCategoria(c);

            //creando objecto usuario
            Usuario u = new Usuario();
            u.setIdusuario(idusuario);
            u.setNombre(nombre);

            lsResp.add(new Iniciativa(idiniciativa, nombreIni, metaeconomica, metatiempo, u, s, 1));
        }
        return lsResp;
    }

    public static Iniciativa getIniciativa(String idIniciativa) throws SQLException {
        Iniciativa ini = null;

        ResultSet rs = queryResultSet("SELECT i.idiniciativa, i.\"nombreIni\", i.metaeconomica, i.metatiempo, u.nombre, s.idsubcategoria, s.\"nombreSub\", c.idcategoria, c.\"nombreCat\", estado\n"
                + "FROM iniciativa i, categoria c, subcategoria s, usuario u\n"
                + "WHERE i.idusuario=u.idusuario AND  i.idsubcategoria=s.idsubcategoria AND s.idcategoria=c.idcategoria AND i.idiniciativa=" + idIniciativa + ";");
        while (rs.next()) {
            //recuperando del result set
            int idiniciativa = rs.getInt("idiniciativa");
            String nombreIni = rs.getString("nombreIni");
            double metaeconomica = rs.getDouble("metaeconomica");
            String metatiempo = rs.getString("metatiempo");
            String nombre = rs.getString("nombre");
            int idsubcategoria = rs.getInt("idsubcategoria");
            String nombreSub = rs.getString("nombreSub");
            int idcategoria = rs.getInt("idcategoria");
            String nombreCat = rs.getString("nombreCat");
            int estado = rs.getInt("estado");

            //creando objeto categoria
            Categoria c = new Categoria();
            c.setIdcategoria(idcategoria);
            c.setNombreCat(nombreCat);

            //creando objecto subcategoria
            Subcategoria s = new Subcategoria();
            s.setIdsubcategoria(idsubcategoria);
            s.setNombreSub(nombreSub);
            s.setCategoria(c);

            //creando objecto usuario
            Usuario u = new Usuario();
            u.setNombre(nombre);

            ini = new Iniciativa(idiniciativa, nombreIni, metaeconomica, metatiempo, u, s, estado);

        }
        return ini;
    }

    public static List<Categoria> lsCategoria() throws SQLException {
        List<Categoria> lsResp = new ArrayList();
        ResultSet rs = queryResultSet("SELECT * FROM categoria");
        while (rs.next()) {
            //recuperando del result set
            int idcategoria = rs.getInt("idcategoria");
            String nombre = rs.getString("nombreCat");
            lsResp.add(new Categoria(idcategoria, nombre));
        }
        return lsResp;
    }

    public static List<Subcategoria> lsSubcategoria(String idCategoria) throws SQLException {
        List<Subcategoria> lsResp = new ArrayList();
        ResultSet rs = queryResultSet("SELECT idsubcategoria, \"nombreSub\", s.idcategoria, \"nombreCat\" FROM subcategoria s, categoria c WHERE s.idcategoria=c.idcategoria and c.idcategoria=" + idCategoria + ";");
        while (rs.next()) {
            //recuperando del result set
            int idsubcategoria = rs.getInt("idsubcategoria");
            String nombreSub = rs.getString("nombreSub");
            int idcategoria = rs.getInt("idcategoria");
            String nombreCat = rs.getString("nombreCat");
            lsResp.add(new Subcategoria(idsubcategoria, nombreSub, new Categoria(idcategoria, nombreCat)));
        }
        return lsResp;
    }
    
    public static List<Subcategoria> lsSubcategoria() throws SQLException {
        List<Subcategoria> lsResp = new ArrayList();
        ResultSet rs = queryResultSet("SELECT idsubcategoria, \"nombreSub\", s.idcategoria, \"nombreCat\" FROM subcategoria s, categoria c WHERE s.idcategoria=c.idcategoria;");
        while (rs.next()) {
            //recuperando del result set
            int idsubcategoria = rs.getInt("idsubcategoria");
            String nombreSub = rs.getString("nombreSub");
            int idcategoria = rs.getInt("idcategoria");
            String nombreCat = rs.getString("nombreCat");
            lsResp.add(new Subcategoria(idsubcategoria, nombreSub, new Categoria(idcategoria, nombreCat)));
        }
        return lsResp;
    }

    public static List<DenunciaEnIni> lsDenunciaEnIni() throws SQLException {
        List<DenunciaEnIni> resp = new ArrayList();
        ResultSet rs = queryResultSet("select c.idcomentario, c.descripcion, u.nombre, i.\"nombreIni\" from comentario c, usuario u, iniciativa i\n"
                + "where c.idusuario=u.idusuario and c.idiniciativa=i.idiniciativa and c.denunciado=1;");
        while (rs.next()) {
            int idcomentario = rs.getInt("idcomentario");
            String descripcion = rs.getString("descripcion");
            String nombre = rs.getString("nombre");
            String nombreIni = rs.getString("nombreIni");
            resp.add(new DenunciaEnIni(idcomentario, descripcion, nombre, nombreIni));
        }
        return resp;
    }

    public static List<Comentario> lsComentario(String idIniciativa) throws SQLException {
        List<Comentario> lsResp = new ArrayList();
        ResultSet rs = queryResultSet("select c.idcomentario, c.descripcion, u.nombre from comentario c, usuario u\n"
                + "where c.idusuario=u.idusuario and c.idiniciativa=" + idIniciativa + ";");
        while (rs.next()) {
            //recuperando del result set
            int idcomentario = rs.getInt("idcomentario");
            String descripcion = rs.getString("descripcion");
            String nombre = rs.getString("nombre");

            lsResp.add(new Comentario(idcomentario, descripcion, nombre));
        }
        return lsResp;
    }

    public static List<Patrocinio> lsPatrocinio(String idusuOidrecomOtodo, String modo) throws SQLException {
        List<Patrocinio> lsResp = new ArrayList();
        ResultSet rs = null;

        switch (modo) {
            case "todo":
                String todosPatrocinios = "SELECT p.idpatrocinio, p.ruta, u.nombre, r.descripcion, i.\"nombreIni\"\n"
                        + "FROM patrocinio p, usuario u, recompensa r, iniciativa i\n"
                        + "WHERE p.idusuario=u.idusuario AND p.idrecompensa=r.idrecompensa AND r.iniciativa=i.idiniciativa;";
                rs = queryResultSet(todosPatrocinios);
                break;
            case "idusu":
                String patrociniosDeUnUsuario = "SELECT p.idpatrocinio, p.ruta, u.nombre, r.descripcion, i.\"nombreIni\"\n"
                        + "FROM patrocinio p, usuario u, recompensa r, iniciativa i\n"
                        + "WHERE p.idusuario=u.idusuario AND p.idrecompensa=r.idrecompensa AND r.iniciativa=i.idiniciativa AND p.idusuario=" + idusuOidrecomOtodo + ";";
                rs = queryResultSet(patrociniosDeUnUsuario);
                break;
            case "idrecom":
                String patrociniosPorRecompensa = "SELECT p.idpatrocinio, p.ruta,  u.nombre, r.descripcion, i.\"nombreIni\"\n"
                        + "FROM patrocinio p, usuario u, recompensa r, iniciativa i\n"
                        + "WHERE p.idusuario=u.idusuario AND p.idrecompensa=r.idrecompensa AND r.iniciativa=i.idiniciativa AND r.idrecompensa=" + idusuOidrecomOtodo + ";";
                rs = queryResultSet(patrociniosPorRecompensa);
                break;
        }

        while (rs.next()) {
            //recuperando del result set
            int idpatrocinio = rs.getInt("idpatrocinio");
            String ruta = rs.getString("ruta");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            String nombreIni = rs.getString("nombreIni");

            lsResp.add(new Patrocinio(idpatrocinio, ruta, nombre, descripcion, nombreIni));
        }
        return lsResp;
    }
    

    public static double recaudacion(String idIniciativa) throws SQLException {
        double resp = -1;
        ResultSet rs = queryResultSet("select sum(r.p_minimo) from patrocinio p, recompensa r, iniciativa i\n"
                + "where p.idrecompensa=r.idrecompensa and r.iniciativa=i.idiniciativa  and i.idiniciativa=" + idIniciativa + ";");
        if (rs.next()) {
            resp = rs.getDouble("sum");
        }
        return resp;
    }

    public static int backers(String idIniciativa) throws SQLException {
        int resp = -1;
        ResultSet rs = queryResultSet("select count(p.idpatrocinio) from patrocinio p, recompensa r, iniciativa i\n"
                + "where p.idrecompensa=r.idrecompensa and r.iniciativa=i.idiniciativa and i.idiniciativa=" + idIniciativa + ";");
        if (rs.next()) {
            resp = rs.getInt("count");
        }
        return resp;
    }

    public static ResultSet queryResultSet(String query) {
        ResultSet rs = null;

        try {
            Connection con = Conexion.getConexion();
            rs = con.createStatement().executeQuery(query);
            con.close();
        } catch (Exception e) {
            System.err.println("Ocurrio excepcion: " + e.getMessage());
        }
        return rs;
    }

    public static int query(String query) throws SQLException {
        Connection con = null;
        Statement sentencia = null;
        int resp = -1;

        try {
            con = Conexion.getConexion();
            sentencia = con.createStatement();
            resp = sentencia.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("query: Ocurrio excepcion: " + e.getMessage() + "  //" + e);
        } finally {

            if (sentencia != null) {
                sentencia.close();
            }

            if (con != null) {
                con.close();
            }

        }
        return resp;
    }

    public static int existeCategoria(String str) throws SQLException {
        ResultSet rs = queryResultSet("SELECT * FROM categoria WHERE \"nombreCat\"='" + str + "'");
        int resp = -1;
        if (rs.next()) {
            resp = rs.getInt("idcategoria");
        } else {
            System.out.println("no existe");
        }
        return resp;
    }

    public static int existeSubcategoria(String str) throws SQLException {
        ResultSet rs = queryResultSet("SELECT * FROM subcategoria WHERE \"nombreSub\"='" + str + "'");
        int resp = -1;
        if (rs.next()) {
            resp = rs.getInt("idsubcategoria");
        } else {
            System.out.println("no existe");
        }
        return resp;
    }

}
