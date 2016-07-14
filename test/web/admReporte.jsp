<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Reportes</title>
    </head>
    <body>
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_administrador.jsp" %>        
    
        <form action="reporte">
            <table border="0" class="table">
                <tbody>
                    <tr>
                        <td><input name="cat" type="submit" class="btn btn-primary btn-lg" value="Financiero"></td>
                    </tr>

                    <tr>
                        <td><input name="cat" type="submit" class="btn btn-primary btn-lg" value="Cliente"></td>
                    </tr>

                    <tr>
                        <td><input name="cat" type="submit" class="btn btn-primary btn-lg" value="Iniciativa"></td>
                    </tr>

                    <tr>
                        <td><input name="cat" type="submit" class="btn btn-primary btn-lg" value="Recompensas mas compradas"></td>
                    </tr>

                    <tr>
                        <td><input name="cat" type="submit" class="btn btn-primary btn-lg" value="Usuarios con mas iniciativas"></td>
                    </tr>

                    <tr>
                        <td><input name="cat" type="submit" class="btn btn-primary btn-lg" value="Usuarios que mas han donado"></td>
                    </tr>

                    <tr>
                        <td><input name="cat" type="submit" class="btn btn-primary btn-lg" value="Usuarios que mas recompensas han comprado"></td>
                    </tr>
                </tbody>
            </table>

            

            
            
            
            
            
            
        </form>
        <%@include file="/plantilla/footer.jsp" %>
    </body>
</html>
