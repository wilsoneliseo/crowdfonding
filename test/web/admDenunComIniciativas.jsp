<%-- 
    Document   : admDenunComIniciativas
    Created on : 9/07/2016, 12:39:06 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Denuncias de comentarios</title>
    </head>
    <body>
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_administrador.jsp" %>

        <c:choose>
            <c:when test="${not empty requestScope.denunciaenini}">
                <h3>Listado de comentarios denunciados en el perfil de iniciativas</h3>
                <table class="table table-striped">
                    <th>Id Comentario</th>
                    <th>Comentario</th>
                    <th>Usuario que comento</th>
                    <th>Iniciativa</th>
                    <th>-</th>
                        <c:forEach items="${requestScope.denunciaenini}" var="d">
                        <tr>
                            <%-- <td><a href="${pageContext.request.contextPath}/GetUsuario?id=${usuario.getIdusuario()}">${usuario.getIdusuario()}</a></td> --%>
                            <td>${d.getIdcomentario()}</td>
                            <td>${d.getDescripcion()}</td>
                            <td>${d.getNombre()}</td>
                            <td>${d.getNombreIni()}</td>
                            <td><a href="${pageContext.request.contextPath}/anularDenunComent?idcomentario=${d.getIdcomentario()}">Anular denuncia</a></td>
                        </tr>
                    </c:forEach>                 
                </table>
            </c:when>
            <c:otherwise>
                <div class="alert-danger"><h4>No existen comentarios denunciados</h4></div>
            </c:otherwise>
        </c:choose>        
        <%@include file="/plantilla/footer.jsp" %>
    </body>
</html>
