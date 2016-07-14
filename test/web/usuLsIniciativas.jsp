<%-- 
    Document   : oso
    Created on : 8/07/2016, 03:26:09 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Listado de iniciativas</title>
    </head>
    <body>
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_usuario.jsp" %>





        <table class="table table-hover">
            <!--            idiniciativa, "nombreIni", metaeconomica, metatiempo, idusuario, idsubcategoria, estado-->
            <th>Id Iniciativa</th>
            <th>Iniciativa</th>
            <th>Meta economica</th>
            <th>Meta tiempo</th>
            <th>Usuario</th>
            <th>Id Subcategoria</th>
            <th>Subcategoria</th>
            <th>Categoria</th>
            <th>-</th>
            <th>-</th>
                <c:forEach items="${requestScope.iniciativas}" var="ini">
                <tr>
                    <td>${ini.getIdiniciativa()}</td>
                    <td>${ini.getNombreIni()}</td>
                    <td>${ini.getMetaeconomica()}</td>
                    <td>${ini.getMetatiempo()}</td>
                    <td>${ini.getUsuario().getNombre()}</td>
                    <td>${ini.getSubcategoria().getIdsubcategoria()}</td>
                    <td>${ini.getSubcategoria().getNombreSub()}</td>
                    <td>${ini.getSubcategoria().getCategoria().getNombreCat()}</td>                    
                    <td><a href="${pageContext.request.contextPath}/GetIniciativa?idEditar=${ini.getIdiniciativa()}">Editar</a></td>
                    <td><a href="${pageContext.request.contextPath}/recompensa.jsp?idinici=${ini.getIdiniciativa()}&nomini=${ini.getNombreIni()}">Gestionar recompensa</a></td>
                </tr>
            </c:forEach>                 
        </table>
        <%@include file="/plantilla/footer.jsp" %>
    </body>
</html>
