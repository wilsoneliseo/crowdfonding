<%-- 
    Document   : cerrar
    Created on : 1/07/2016, 03:11:00 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%    
    session.invalidate();
    response.sendRedirect("index.jsp");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><p>A salido de la sesion.
            <p><a href="index.jsp">Volver</a>
        </h1>
    </body>
</html>
