<%-- 
    Document   : adm.listaUsuarios
    Created on : 6/07/2016, 11:18:49 AM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuarios</title>
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_administrador.jsp" %>
        
        <h2>Lista de usuarios</h2>
        <br />
        <table class="table table-striped">
            <th>Id Usuario</th>
            <th>Nickname</th>
            <th>Contraseña</th>
            <th>Nombre</th>
            <th>Direccion</th>
            <th>Telefono</th>
            <th>Cuenta Bancaria</th>
            <th>Tipo</th>
            <c:forEach items="${requestScope.usuarios}" var="usuario">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/GetUsuario?id=${usuario.getIdusuario()}">${usuario.getIdusuario()}</a></td>
                    <td>${usuario.getNickname()}</td>
                    <td>${usuario.getContrasena()}</td>
                    <td>${usuario.getNombre()}</td>
                    <td>${usuario.getDireccion()}</td>
                    <td>${usuario.getTelefono()}</td>
                    <td>${usuario.getCuentabancaria()}</td>
                    <td>${usuario.getTipo().name()}</td>
                </tr>
            </c:forEach>                 
        </table>
        <%@include file="/plantilla/footer.jsp" %>
    </body>
</html>
