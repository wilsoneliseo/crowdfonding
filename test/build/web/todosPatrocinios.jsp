<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Todas las recompensas</title>
    </head>
    <body>
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_usuario.jsp" %>

        <%        try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();

                java.util.List<west.ws.Patrocinio> result = port.lsPatrocinioWS("todo", "todo");
                request.setAttribute("patrocinios", result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>


        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Todos los usuarios que han adquirido recompensas</h3>
            </div>
            <div class="panel-body">
                <c:choose>
                    <c:when test="${not empty patrocinios}">
                        <table class="table table-striped">
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
                        <div class="alert-danger"><h4>No hay recompensas compradas o adquiridas</h4></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <%@include file="/plantilla/footer.jsp" %>
    </body>
</html>