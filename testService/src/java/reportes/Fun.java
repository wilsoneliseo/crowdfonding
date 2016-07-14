/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import reportes.mapeos.RecaudacionSubcategoria;
import west.dao.Conexion;

public class Fun {
    
    public static List<String> top(String modo) throws SQLException {
        List<String> resp = new ArrayList();
        String query = null;
        ResultSet rs;

        switch (modo) {
            case "rmc":
                query = "select r.descripcion, count(p.idrecompensa) as total \n"
                        + "from patrocinio p, recompensa r\n"
                        + "where p.idrecompensa=r.idrecompensa\n"
                        + "group by r.descripcion\n"
                        + "order by 2 desc";
                rs = queryResultSet(query);
                while (rs.next()) {
                    resp.add(rs.getString("descripcion"));
                    resp.add(rs.getString("total"));
                }
                break;
            case "ucmi":
                query = "select u.nombre, count(i.idusuario) as total \n"
                        + "from iniciativa i, usuario u\n"
                        + "where i.idusuario=u.idusuario\n"
                        + "group by u.nombre \n"
                        + "order by 2 desc";
                rs = queryResultSet(query);
                while (rs.next()) {
                    resp.add(rs.getString("nombre"));
                    resp.add(rs.getString("total"));
                }
                break;
            case "uqmdhd":
                query = "select u.nombre, sum(r.p_minimo) as total\n"
                        + "from patrocinio p, recompensa r, usuario u\n"
                        + "where p.idrecompensa=r.idrecompensa and p.idusuario=u.idusuario\n"
                        + "group by u.nombre\n"
                        + "order by 2 desc";
                rs = queryResultSet(query);
                while (rs.next()) {
                    resp.add(rs.getString("nombre"));
                    resp.add(rs.getString("total"));
                }
                break;
            case "uqmrhc":
                query = "select u.nombre, count(p.idusuario) as total \n"
                        + "from patrocinio p, usuario u\n"
                        + "where p.idusuario=u.idusuario\n"
                        + "group by u.nombre \n"
                        + "order by 2 desc";
                rs = queryResultSet(query);
                while (rs.next()) {
                    resp.add(rs.getString("nombre"));
                    resp.add(rs.getString("total"));
                }
                break;
        }

        return resp;
    }

    public static List<String> recomCompradaIni() throws SQLException {
        List<String> resp = new ArrayList();
        String query = null;

        query = "SELECT i.idiniciativa, i.\"nombreIni\", p.idpatrocinio, r.descripcion, u.nombre\n"
                + "FROM patrocinio p, usuario u, recompensa r, iniciativa i\n"
                + "WHERE p.idusuario=u.idusuario AND p.idrecompensa=r.idrecompensa AND r.iniciativa=i.idiniciativa\n"
                + "ORDER BY i.idiniciativa;";

        ResultSet rs = rs = queryResultSet(query);
        while (rs.next()) {
            resp.add(rs.getString("idiniciativa"));
            resp.add(rs.getString("nombreIni"));
            resp.add(rs.getString("idpatrocinio"));
            resp.add(rs.getString("descripcion"));
            resp.add(rs.getString("nombre"));
        }
        return resp;
    }

    public static List<String> recomComprada() throws SQLException {
        List<String> resp = new ArrayList();
        String query = null;

        query = "SELECT  u.idusuario, u.nombre, p.idpatrocinio, r.descripcion, i.\"nombreIni\"\n"
                + "FROM patrocinio p, usuario u, recompensa r, iniciativa i\n"
                + "WHERE p.idusuario=u.idusuario AND p.idrecompensa=r.idrecompensa AND r.iniciativa=i.idiniciativa\n"
                + "ORDER BY u.idusuario;";

        ResultSet rs = rs = queryResultSet(query);
        while (rs.next()) {
            resp.add(rs.getString("idusuario"));
            resp.add(rs.getString("nombre"));
            resp.add(rs.getString("idpatrocinio"));
            resp.add(rs.getString("descripcion"));
            resp.add(rs.getString("nombreIni"));
        }
        return resp;
    }

    public static List<String> reporteCategoria(String op) throws SQLException {
        List<String> resp = new ArrayList();
        String query = null;

        query = "select c.\"nombreCat\", " + op + "(r.p_minimo) from patrocinio p, recompensa r, iniciativa i, subcategoria s, categoria c\n"
                + "where p.idrecompensa=r.idrecompensa and r.iniciativa=i.idiniciativa and i.idsubcategoria=s.idsubcategoria and s.idcategoria=c.idcategoria\n"
                + "group by c.\"nombreCat\";";

        ResultSet rs = rs = queryResultSet(query);
        while (rs.next()) {
            String nombreSub = rs.getString("nombreCat");
            resp.add(nombreSub);
            double sum = rs.getDouble(op);
            resp.add(String.valueOf(sum));
        }
        return resp;
    }

    public static List<String> lsRecaduacionSubcategoria(String op) throws SQLException {
        List<String> resp = new ArrayList();
        String query = null;

        query = "select s.\"nombreSub\", " + op + "(r.p_minimo) from patrocinio p, recompensa r, iniciativa i, subcategoria s\n"
                + "where p.idrecompensa=r.idrecompensa and r.iniciativa=i.idiniciativa and i.idsubcategoria=s.idsubcategoria\n"
                + "group by s.\"nombreSub\";";

        ResultSet rs = rs = queryResultSet(query);
        while (rs.next()) {
            String nombreSub = rs.getString("nombreSub");
            resp.add(nombreSub);
            double sum = rs.getDouble(op);
            resp.add(String.valueOf(sum));
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
}
