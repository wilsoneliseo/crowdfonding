<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>        
        <title>Administrador Logeado</title>
        <script>
            window.addEventListener('load', inicio, false);

            function inicio() {
                document.getElementById('archivo').addEventListener('change', cargar, false);
            }

            function cargar(ev) {
                document.getElementById('datos').innerHTML = 'Nombre del archivo:' + ev.target.files[0].name + '<br>' +
                        'Tamaño del archivo:' + ev.target.files[0].size + '<br>' +
                        'Tipo MIME:' + ev.target.files[0].type;
                var arch = new FileReader();
                arch.addEventListener('load', leer, false);
                arch.readAsText(ev.target.files[0]);
            }

            function leer(ev) {
                document.getElementById('editor').value = ev.target.result;
            }
        </script>
    </head>
    <body>
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_administrador.jsp" %>

        <!-- ------------------------------------------MODULO DE USUARIOS --> 

        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Modulo de usuarios</h3>
            </div>
            <div class="panel-body">          

                <form class="form-horizontal" action="opUsuarioServlet" method="POST">
                    <div class="form-group">
                        <label for="id" class="col-lg-2 control-label">Identificador</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="id" value="${usuario.getIdusuario()}" placeholder="Identificador de usuario">
                        </div>
                    </div>            
                    <div class="form-group">
                        <label for="nik" class="col-lg-2 control-label">Nickname</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="nik" value="${usuario.getNickname()}" placeholder="Ingrese su Nickname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="contra" class="col-lg-2 control-label">Contraseña</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="contra" value="${usuario.getContrasena()}" placeholder="Contraseña">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nombre" class="col-lg-2 control-label">Nombre</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="nombre" value="${usuario.getNombre()}" placeholder="Nombre completo">
                        </div>
                    </div>   
                    <div class="form-group">
                        <label for="direccion" class="col-lg-2 control-label">Direccion</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="direccion" value="${usuario.getDireccion()}" placeholder="Direccion">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tel" class="col-lg-2 control-label">Telefono</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="tel" value="${usuario.getTelefono()}" placeholder="Telefono">
                        </div>
                    </div>            
                    <div class="form-group">
                        <label for="cuenta" class="col-lg-2 control-label">Cuenta Bancaria</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="cuenta" value="${usuario.getCuentabancaria()}" placeholder="Cuenta bancaria">
                        </div>
                    </div>

                    <select name="tipo">
                        <option>Administrador</option>
                        <option>Usuario</option>
                    </select>

                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" class="btn btn-default" name="operacion" value="Crear">
                            <input type="submit" class="btn btn-default" name="operacion" value="Modificar">
                            <input type="submit" class="btn btn-default" name="operacion" value="Eliminar">                    
                        </div>
                    </div>

                </form>


                <div class="alert alert-warning">
                    <c:out value="${msj}" />
                </div>
            </div><!--fin del panel-body-->
        </div><!--fin del panel-->



        <!-- ------------------------------------------MODULO DE CATEGORIAS -->
        <%            try {
                String boton = null;
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                String query = "";
                int resultado = -1;
                int resaux = -1;

                if ((boton = request.getParameter("opCat")) != null) {
                    String idCategoria = request.getParameter("idCategoria");
                    String nombreCategoria = request.getParameter("nombreCategoria");
                    switch (boton) {
                        case "Crear":
                            query = "INSERT INTO categoria(idcategoria,\"nombreCat\") VALUES(default,'" + nombreCategoria + "');";
                            resultado = port.queryWS(query);

                            if (resultado > 0) {
                                request.setAttribute("msjc", "Se creo la Categoria");
                            } else {
                                request.setAttribute("msjc", "NO se creo la Categoria");
                            }
                            break;
                        case "Modificar":
                            query = "UPDATE categoria SET  \"nombreCat\"='" + nombreCategoria + "' WHERE idcategoria='" + idCategoria + "';";
                            resultado = port.queryWS(query);

                            if (resultado > 0) {
                                request.setAttribute("msjc", "Se modifico la Categoria");
                            } else {
                                request.setAttribute("msjc", "NO se modifico la Categoria");
                            }
                            break;
                        case "Eliminar":
                            resaux = port.queryWS("DELETE FROM subcategoria WHERE idcategoria=" + idCategoria + ";");
                            query = "DELETE FROM categoria WHERE idcategoria=" + idCategoria + ";";

                            resultado = port.queryWS(query);

                            if (resultado > 0 && resaux > 0) {
                                request.setAttribute("msjc", "Se elimino la Categoria");
                            } else {
                                request.setAttribute("msjc", "NO se elimino Categoria");
                            }
                            break;
                    }
                }
            } catch (Exception ex) {
                System.out.println("adm.jsp excepcion: " + ex + "   \\" + ex.getMessage());
            }
        %>

        <%
            try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                // TODO process result here
                java.util.List<west.ws.Categoria> lscategoria = port.lsCategoriaWS();
                request.setAttribute("lscategoria", lscategoria);
            } catch (Exception ex) {
                System.out.println("adm.jsp excepcion: " + ex + "   \\" + ex.getMessage());
            }
        %>
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Categorias</h3>
            </div>
            <div class="panel-body">
                <h4>Crear Categoria</h4>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="idCategoria" class="col-lg-2 control-label">Identificador</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="idCategoria"  placeholder="Identificador de categoria">
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="nombreCategoria" class="col-lg-2 control-label">Nombre</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="nombreCategoria" placeholder="Nombre de categoria">
                        </div>
                    </div>         


                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" class="btn btn-default" name="opCat" value="Crear">
                            <input type="submit" class="btn btn-default" name="opCat" value="Modificar">
                            <input type="submit" class="btn btn-default" name="opCat" value="Eliminar">
                        </div>
                    </div>



                    <div class="alert alert-warning">
                        <c:out value="${msjc}"/>
                    </div>



                    <c:if test="${not empty lscategoria}">
                        <h4>Listado de categorias</h4>
                        <table class="table table-hover">
                            <th>Identificador de Categoria</th>
                            <th>Nombre de Categoria</th>
                                <c:forEach var="cat"  items="${lscategoria}">
                                <tr>
                                    <td><c:out value="${cat.getIdcategoria()}"/></td>
                                    <td><c:out value="${cat.getNombreCat()}"/></td>
                                </tr>
                            </c:forEach>
                        </table>                        
                    </c:if>
                </form>
            </div>  <!--fin del panel-body-->
        </div><!-- fin del panel categorias-->



        <!-- ------------------------------------------MODULO DE SUBCATEGORIAS -->        
        <%
            try {

                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                String botonsub = null;
                String query = "";
                int resultado = -1;

                if ((botonsub = request.getParameter("opSubCat")) != null) {
                    String idSubCategoria = request.getParameter("idSubCategoria");
                    String nombreSubCategoria = request.getParameter("nombreSubCategoria");
                    String idcatselect = request.getParameter("idCatSelec");

                    switch (botonsub) {
                        case "Crear":
                            query = "INSERT INTO subcategoria(idsubcategoria, \"nombreSub\", idcategoria) VALUES (default, '" + nombreSubCategoria + "', " + idcatselect + ");";
                            resultado = port.queryWS(query);

                            if (resultado > 0) {
                                request.setAttribute("msjs", "Se creo la subcategoria");
                            } else {
                                request.setAttribute("msjs", "NO se creo la subcategoria");
                            }
                            break;
                        case "Modificar":
                            query = "UPDATE subcategoria SET idsubcategoria=" + idSubCategoria + ", \"nombreSub\"='" + nombreSubCategoria + "', idcategoria=" + idcatselect + " WHERE idsubcategoria=" + idSubCategoria + ";";
                            resultado = port.queryWS(query);

                            if (resultado > 0) {
                                request.setAttribute("msjs", "Se modifico la subcategoria");
                            } else {
                                request.setAttribute("msjs", "NO se modificola subcategoria");
                            }
                            break;
                        case "Eliminar":
                            query = "DELETE FROM subcategoria WHERE idsubcategoria='" + idSubCategoria + "';";
                            resultado = port.queryWS(query);

                            if (resultado > 0) {
                                request.setAttribute("msjs", "Se elimino la subcategoria");
                            } else {
                                request.setAttribute("msjs", "NO se elimino la subcategoria");
                            }
                            break;
                    }
                }//if
            } catch (Exception ex) {
                System.out.println("adm.jsp Excepcion: " + ex + "   //" + ex.getMessage());
            }
        %>


        <%
            try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                java.util.List<west.ws.Subcategoria> lisSub = null;

                lisSub = port.lsTodasSubcategoriasWS();
                request.setAttribute("lsubcategoria", lisSub);
            } catch (Exception ex) {
                System.out.println("adm.jsp Excepcion " + ex + "   \\" + ex.getMessage());
            }
        %>

        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Sub Categorias</h3>
            </div>
            <div class="panel-body">
                <h4>Crear Sub Categoria</h4>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="idSubCategoria" class="col-lg-2 control-label">Identificador Subcategoria</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="idSubCategoria"  placeholder="Identificador de subcategoria">
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="nombreSubCategoria" class="col-lg-2 control-label">Nombre Subcategoria</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="nombreSubCategoria" placeholder="Nombre de subcategoria">
                        </div>
                    </div>                

                    <select name="idCatSelec">
                        <c:forEach items="${lscategoria}" var="cat">
                            <option value="${cat.getIdcategoria()}">${cat.getNombreCat()}</option>
                        </c:forEach>
                    </select>

                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" class="btn btn-default" name="opSubCat" value="Crear">
                            <input type="submit" class="btn btn-default" name="opSubCat" value="Modificar">
                            <input type="submit" class="btn btn-default" name="opSubCat" value="Eliminar">                            
                        </div>
                    </div>


                    <div class="alert alert-warning">
                        <c:out value="${msjs}"/>
                    </div>                    

                    <c:choose>
                        <c:when test="${not empty lsubcategoria}">
                            <h4>Listado de subcategorias</h4>
                            <table class="table table-striped">
                                <th>Identificador de subcategoria</th>
                                <th>Nombre de subcategoria</th>
                                <th>Nombre categoria</th>
                                    <c:forEach var="sub"  items="${lsubcategoria}">
                                    <tr>
                                        <td><c:out value="${sub.getIdsubcategoria()}"/></td>
                                        <td><c:out value="${sub.getNombreSub()}"/></td>                                    
                                        <td>${sub.getCategoria().getNombreCat()}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                            <c:otherwise>
                                <div class="alert-danger"><h3>No hay subcategorias</h3></div>
                            </c:otherwise>
                    </c:choose>
                </form>

            </div>  <!--fin del panel-body-->
        </div><!-- fin del panel SUB categorias-->

        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Cargar categorias y subcategorias</h3>
            </div>
            <div class="panel-body">
                <form action="cargarServlet">
                    <input type="file" id="archivo"><br>
                    <textarea rows="10" cols="80" id="editor" name="d"></textarea>
                    <br>
                    <p id="datos"></p>
                    <input type="submit" value="Analizar">
                </form>
            </div>
        </div>



        <%@include file="/plantilla/footer.jsp" %>
    </body>
</html>
