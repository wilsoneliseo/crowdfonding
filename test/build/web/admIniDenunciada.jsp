<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Iniciativas denunciadas</title>
    </head>
    <body>
        <%@include file="plantilla/header.jsp" %>
        <%@include file="plantilla/navbar_administrador.jsp" %>


        <c:choose>
            <c:when test="${not empty iniDenunciada}">
                <h3>Iniciativas denunciadas de todos los usuarios</h3>
                <table class="table table-striped">
                    <th>Id Iniciativa</th>
                    <th>Iniciativa</th>
                    <th>Meta economica</th>
                    <th>Meta tiempo</th>
                    <th>Emprendedor</th>
                    <th>Id Sub</th>
                    <th>Subcategoria</th>
                    <th>Categoria</th>
                    <th>-</th>
                    <c:forEach items="${iniDenunciada}" var="ini">
                        <tr>
                            <td>${ini.getIdiniciativa()}</td>
                            <td>${ini.getNombreIni()}</td>
                            <td>${ini.getMetaeconomica()}</td>
                            <td>${ini.getMetatiempo()}</td>
                            <td>${ini.getUsuario().getNombre()}</td>
                            <td>${ini.getSubcategoria().getIdsubcategoria()}</td>
                            <td>${ini.getSubcategoria().getNombreSub()}</td>
                            <td>${ini.getSubcategoria().getCategoria().getNombreCat()}</td>
                            <td><a href="${pageContext.request.contextPath}/anularDenunIni?idini=${ini.getIdiniciativa()}">Anular denuncia</a></td>
                        </tr>
                    </c:forEach>                 
                </table>
            </c:when>
            <c:otherwise>
                <div class="alert-danger"><h4>No existe Iniciativas denunciadas</h4></div>
            </c:otherwise>
        </c:choose>


        <%@include file="plantilla/footer.jsp" %>
    </body>
</html>
