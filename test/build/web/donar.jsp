<%@page import="west.ws.Patrocinio"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Compras para la recompensa: <%=request.getParameter("descr")%></title>
    </head>
    <body>
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_usuario.jsp" %>



        <%            String cantidaRecompensa = request.getParameter("cant");
            String limitado = null;
            if (cantidaRecompensa.equalsIgnoreCase("-1")) {
                limitado = "Tipo ilimitado";
            } else {
                limitado = ("Tipo limitada");
            }
            try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                java.lang.String query = "";
                int resultado = -1;
                String boton = null;
                if ((boton = request.getParameter("operacion")) != null) {
                    String id = request.getParameter("id");
                    String ruta = request.getParameter("ruta");
                    String recompensa = request.getParameter("idrecompensa");

                    int idusuario = usuarioActual.getIdusuario();
                    int dec = -1;

                    switch (boton) {
                        case "Comprar":
                            if (Integer.parseInt(cantidaRecompensa) > 0) {
                                dec = port.queryWS("update recompensa set cantidad=cantidad-1 where idrecompensa=" + recompensa + ";");
                                query = "INSERT INTO patrocinio(idpatrocinio, ruta, costoenvio, idusuario, idrecompensa)\n"
                                        + "VALUES (default, '" + ruta + "', 0, " + idusuario + ", " + recompensa + ");";
                                resultado = port.queryWS(query);

                                if (resultado > 0 && dec > 0) {
                                    request.setAttribute("msj", "Se dono la recompensa");
                                } else {
                                    if (dec > 0) {
                                        port.queryWS("update recompensa set cantidad=cantidad+1 where idrecompensa=" + recompensa + ";");
                                    }
                                    request.setAttribute("msj", "NO se dono la recompensa");
                                }
                            } else if (Integer.parseInt(cantidaRecompensa) == 0) {
                                request.setAttribute("msj", "Recompensa Agotada");
                            } else {
                                query = "INSERT INTO patrocinio(idpatrocinio, ruta, costoenvio, idusuario, idrecompensa)\n"
                                        + "VALUES (default, '" + ruta + "', 0, " + idusuario + ", " + recompensa + ");";
                                resultado = port.queryWS(query);

                                if (resultado > 0) {
                                    request.setAttribute("msj", "Se dono la recompensa");
                                } else {
                                    request.setAttribute("msj", "NO se dono la recompensa");
                                }
                            }
                            break;
                        case "Modificar":
                            query = "UPDATE patrocinio SET  ruta='" + ruta + "', \n"
                                    + "costoenvio=0, idusuario=" + idusuario + ", idrecompensa=" + recompensa + "\n"
                                    + "WHERE idpatrocinio=" + id + ";";
                            resultado = port.queryWS(query);

                            if (resultado > 0) {
                                request.setAttribute("msj", "Se modifico la donacion");
                            } else {
                                request.setAttribute("msj", "NO se modifico la donacion");
                            }
                            break;
                        case "Eliminar":
                            if (Integer.parseInt(cantidaRecompensa) >= 0) {
                                query = "DELETE FROM patrocinio WHERE idpatrocinio=" + id;
                                dec = port.queryWS("update recompensa set cantidad=cantidad+1 where idrecompensa=" + recompensa + ";");
                                resultado = port.queryWS(query);
                                if (resultado > 0 && dec > 0) {
                                    request.setAttribute("msj", "Se elimino la donacion");
                                } else {
                                    if (dec > 0) {
                                        dec = port.queryWS("update recompensa set cantidad=cantidad-1 where idrecompensa=" + recompensa + ";");
                                    }
                                    request.setAttribute("msj", "NO se elimino la donacion");
                                }
                            } else {
                                query = "DELETE FROM patrocinio WHERE idpatrocinio=" + id;
                                resultado = port.queryWS(query);
                                if (resultado > 0) {
                                    request.setAttribute("msj", "Se elimino la donacion");
                                } else {
                                    request.setAttribute("msj", "NO se elimino la donacion");
                                }
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }

                }
            } catch (Exception ex) {
                System.err.println("usu.jsp: una excepcion: " + ex + " //msj: " + ex.getMessage());
            }
        %>

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Comprar recompensa <span style="font-size: 24px;"><%=request.getParameter("descr")%>. <%= limitado%></span></h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" method="post">
                    <div class="form-group">
                        <label for="id" class="col-lg-2 control-label">Id Patrocinio</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="id"  placeholder="Identificador de patrocinio">
                        </div>
                    </div>            
                    <div class="form-group">
                        <label for="ruta" class="col-lg-2 control-label">Ruta</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="ruta"  placeholder="Si no es fisica no agregar nada">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" class="btn btn-default" name="operacion" value="Comprar">
                            <input type="submit" class="btn btn-default" name="operacion" value="Modificar">
                            <input type="submit" class="btn btn-default" name="operacion" value="Eliminar">
                        </div>
                    </div>

                    <div class="alert alert-warning">
                        <c:out value="${msj}"/>
                    </div>

                </form>                                                               
            </div>
        </div>


        <%
            try {
                String recompensa = request.getParameter("idrecompensa");
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();

                java.util.List<west.ws.Patrocinio> lp = port.lsPatrocinioWS(recompensa, "idrecom");
                request.setAttribute("patrocinios", lp);
            } catch (Exception ex) {
                System.out.println("donar.jsp: " +ex+"   //" +ex.getMessage());
            }
        %>

        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">Compras para la recompensa: <strong><%=request.getParameter("descr")%></strong></h3>
            </div>
            <div class="panel-body">
                <c:choose>
                    <c:when test="${not empty patrocinios}">
                        <table class="table table-hover">
                            <th>Id patrocinio</th>
                            <th>Ruta</th>
                            <th>Usuario que lo compro</th>
                            <th>Descripcion de la recompensa</th>
                            <th>Iniciativa</th>
                                <c:forEach items="${patrocinios}" var="p">
                                <tr>
                                    <td>${p.getIdpatrocinio()}</td>
                                    <c:choose>
                                        <c:when test="${p.getRuta()==null || p.getRuta().isEmpty()}">
                                            <td>No fisica</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${p.getRuta()}</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${p.getNombre()}</td>
                                    <td>${p.getDescripcion()}</td>
                                    <td>${p.getNombreIni()}</td>
                                </c:forEach>                 
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="alert-danger"><h4>Aun no ha adquirido recompensas</h4></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
