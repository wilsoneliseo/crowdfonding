package west.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String db_driver="org.postgresql.Driver";
    private static final String str_conexion = "jdbc:postgresql://localhost:5432/crowd";
    private static  Connection con;

    public static Connection getConexion() {

        try {
            Class.forName(db_driver);

            try {
                con = DriverManager.getConnection(str_conexion , "wadmin", "wadmin");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        return con;
    }
}
