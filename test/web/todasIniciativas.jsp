<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Iniciativas de todos los usuarios</title>
    </head>
    <body>
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_usuario.jsp" %>

        <%            try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                java.util.List<west.ws.Iniciativa> result = port.lsIniciativaWS("*");
                request.setAttribute("iniciativas", result);
                pageContext.setAttribute("idusuar", usuarioActual.getIdusuario());
            } catch (Exception ex) {
                System.err.println("usu.jsp: una excepcion: " + ex + " //msj: " + ex.getMessage());
            }
        %>

        


        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Iniciativas de todos los usuarios</h3>
            </div>
            <div class="panel-body">
                <c:choose>
                    <c:when test="${not empty iniciativas}">
                        <table class="table table-striped">
                            <th>Id Iniciativa</th>
                            <th>Iniciativa</th>
                            <th>Meta economica</th>
                            <th>Meta tiempo</th>
                            <th>Emprendedor</th>
                            <th>Id Sub</th>
                            <th>Subcategoria</th>
                            <th>Categoria</th>
                            <th>Com</th>
                            <th>-</th>
                            <th>-</th>
                                <c:forEach items="${iniciativas}" var="ini">
                                <tr>
                                    <td>${ini.getIdiniciativa()}</td>
                                    <td>${ini.getNombreIni()}</td>
                                    <td>${ini.getMetaeconomica()}</td>
                                    <td>${ini.getMetatiempo()}</td>
                                    <td>${ini.getUsuario().getNombre()}</td>
                                    <td>${ini.getSubcategoria().getIdsubcategoria()}</td>
                                    <td>${ini.getSubcategoria().getNombreSub()}</td>
                                    <td>${ini.getSubcategoria().getCategoria().getNombreCat()}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${idusuar != ini.getUsuario().getIdusuario()}">
                                                <c:set var="puedeDonar" value="1"></c:set>
                                                    Si
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="puedeDonar" value="0"></c:set>
                                                    No
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="${pageContext.request.contextPath}/ComentarioServlet?id=${ini.getIdiniciativa()}&puedeDonar=${puedeDonar}">Perfil</a></td>
                                    <td><a href="${pageContext.request.contextPath}/denunciarIniServlet?id=${ini.getIdiniciativa()}">Denunciar</a></td>
                                </tr>
                            </c:forEach>                 
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="alert-danger"><h4>No existe Iniciativas</h4></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

    </body>
</html>
