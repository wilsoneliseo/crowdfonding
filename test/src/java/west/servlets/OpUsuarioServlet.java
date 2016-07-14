/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package west.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import west.ws.TipoUsuario;
import west.ws.Usuario;

@WebServlet(name = "opUsuarioServlet", urlPatterns = {"/opUsuarioServlet"})
public class OpUsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //obteniendo los datos de adm.jsp
        String id = request.getParameter("id");
        String nick = request.getParameter("nik");
        String contra = request.getParameter("contra");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String tel = request.getParameter("tel");
        String cuenta = request.getParameter("cuenta");
        String tipo = request.getParameter("tipo");

        String operacion = request.getParameter("operacion");

        Usuario usu = new Usuario();

        //creando el objeto con los datos obtenidos
        usu.setIdusuario(Integer.parseInt(id));
        usu.setNickname(nick);
        usu.setContrasena(contra);
        usu.setNombre(nombre);
        usu.setDireccion(direccion);
        usu.setTelefono(tel);
        usu.setCuentabancaria(cuenta);
        if (tipo.equals("Administrador")) {
            usu.setTipo(TipoUsuario.ADMINISTRADOR);
        } else if (tipo.equals("Usuario")) {
            usu.setTipo(TipoUsuario.USUARIO);
        }

        boolean resp = false;
        String msj = "N/A";

        if (operacion.equalsIgnoreCase("Crear")) {
            resp = crearUsuarioWS(usu);
            request.setAttribute("usuario", usu);
            if (resp) {
                msj = "Usuario creado correctamente.";
            } else {
                msj = "Usuario NO creado.";
            }

        } else if (operacion.equalsIgnoreCase("Modificar")) {
            resp = modificarUsuarioWS(usu);
            request.setAttribute("usuario", usu);
            if (resp) {
                msj = "Usuario modificado correctamente.";
            } else {
                msj = "Usuario NO modificado correctamente.";
            }
        } else if (operacion.equalsIgnoreCase("Eliminar")) {
            int re = queryWS("DELETE FROM usuario WHERE idusuario=" + id + ";");
            if (re > 0) {
                msj = "Se elimino el usuario";
            } else {
                msj = "No se elimino el usuario";
            }
        } else {
            msj = "No se realizo ninguna accion";
        }

        request.setAttribute("msj", msj);
        request.getRequestDispatcher("adm.jsp").forward(request, response);

    }

    private static Boolean crearUsuarioWS(west.ws.Usuario usu) {
        west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
        west.ws.PruebaServicio port = service.getPruebaServicioPort();
        return port.crearUsuarioWS(usu);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static boolean modificarUsuarioWS(west.ws.Usuario usu) {
        west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
        west.ws.PruebaServicio port = service.getPruebaServicioPort();
        return port.modificarUsuarioWS(usu);
    }

    private static Integer queryWS(java.lang.String query) {
        west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
        west.ws.PruebaServicio port = service.getPruebaServicioPort();
        return port.queryWS(query);
    }

}
