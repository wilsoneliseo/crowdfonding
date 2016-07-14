
<%@page import="west.ws.Recompensa"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Comentarios</title>
    </head>
    <body>
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_usuario.jsp" %>



        <%            String id = request.getParameter("id");
            try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                java.lang.String query = "";
                int resultado = -1;
                String boton = null;
                if ((boton = request.getParameter("operacion")) != null) {

                    String coment = request.getParameter("coment");
                    if (boton.equalsIgnoreCase("Comentar")) {
                        query = "INSERT INTO comentario(idcomentario, descripcion, \n"
                                + "idiniciativa, denunciado, idusuario)\n"
                                + "VALUES (default, '" + coment + "', " + id + ", 0, " + usuarioActual.getIdusuario() + ");";
                        resultado = port.queryWS(query);

                        if (resultado > 0) {
                            request.setAttribute("msj", "Comentario realizado");
                        } else {
                            request.setAttribute("msj", "Comentario no realizado");
                        }
                    } else if (boton.equalsIgnoreCase("Modificar")) {

                    }
                }

            } catch (Exception ex) {
                System.err.println("comentario.jsp: una excepcion: " + ex + " //msj: " + ex.getMessage());
            }
        %>


        <%
            try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();

                java.util.List<west.ws.Comentario> lscom = port.lsComentarioWS(id);
                List<Recompensa> lsrecom = port.lsRecompensaWS(id, "porIniciativa");
                request.setAttribute("comentarios", lscom);
                request.setAttribute("recompensas", lsrecom);
                
                //recaudacion
                double rec=port.recaudacionWS(id);
                //backers
                int backers=port.backersWS(id);
                request.setAttribute("recaudacion", rec);
                request.setAttribute("backers", backers);

            } catch (Exception ex) {
                System.err.println("comentario.jsp: una excepcion: " + ex + " //msj: " + ex.getMessage());
            }
        %>



        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Iniciativa</h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="id" class="col-lg-2 control-label">Identificador</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="id"  value="${iniciativa.getIdiniciativa()}" placeholder="Identificador">
                        </div>
                    </div>

                    <div class="jumbotron">

                        <table class="table-hover" width="100%">
                            <tbody>
                                <tr>
                                    <td><span class="text-success" style="font-size: 24px;">Meta economica</span></td>
                                    <td><span class="text-success" style="font-size: 24px;">Recaudado</span></td>
                                    <td><span class="text-success" style="font-size: 24px;">Backers</span></td>
                                </tr>
                                <tr>
                                    <td>
                                        <blockquote>
                                            <p><span style="font-size: 36px;">${iniciativa.getMetaeconomica()}</span></p>
                                            <small>Iniciativa: ${iniciativa.getNombreIni()}</small>
                                        </blockquote>
                                    </td>
                                    <td>
                                        <blockquote>
                                            <p><span style="font-size: 36px;">${recaudacion}</span></p>
                                            <small>Subcategoria: ${iniciativa.getSubcategoria().getNombreSub()}</small>
                                        </blockquote>
                                    </td>
                                    <td>
                                        <blockquote>
                                            <p><span style="font-size: 36px;">${backers}</span></p>
                                            <small>Categoria: ${iniciativa.getSubcategoria().getCategoria().getNombreCat()}</small>
                                        </blockquote>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                    </div>


                    <div class="form-group">
                        <label for="coment" class="col-lg-2 control-label">Comentario</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="coment"  placeholder="Ingrese su comentario">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" class="btn btn-default" name="operacion" value="Comentar">             
                        </div>
                    </div>


                    <div class="alert alert-warning">
                        <c:out value="${msj}"/>
                    </div>
                </form>
            </div>
        </div>






        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Listado de recompensas</h3>
            </div>
            <div class="panel-body">
                <c:choose>
                    <c:when test="${not empty recompensas}">
                        <table class="table table-hover">                            
                            <th>Id recompensa </th>
                            <th>Descripcion</th>
                            <th>Precio minimo</th>
                            <th>Cantidad</th>
                            <th>Iniciativa</th>
                            <th>-</th>
                                <c:forEach var="r"  items="${recompensas}">
                                <tr>
                                    <td><c:out value="${r.getIdrecompensa()}"/></td>
                                    <td><c:out value="${r.getDescripcion()}"/></td>
                                    <td><c:out value="${r.getPMinimo()}"/></td>
                                    <c:choose>
                                        <c:when test="${r.getCantidad()>0}">
                                            <td>${r.getCantidad()}</td>                                
                                        </c:when>
                                        <c:otherwise>
                                            <td>Ilimitado</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td><c:out value="${r.getNombreIni()}"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${puedeDonar == 1}">
                                                <a href="donar.jsp?idrecompensa=${r.getIdrecompensa()}&descr=${r.getDescripcion()}&cant=${r.getCantidad()}">Comprar</a>
                                            </c:when>
                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table> 
                    </c:when>
                    <c:otherwise>
                        <div class="alert-success"><h3>No hay recompensas</h3></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>


        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">Listado de comentarios</h3>
            </div>
            <div class="panel-body">
                <c:choose>
                    <c:when test="${not empty comentarios}">
                        <table class="table table-hover">                            
                            <th>Identificador de comentario</th>
                            <th>Comentario</th>
                            <th>Lo comento el usuario</th>
                            <th>-</th>
                                <c:forEach var="c"  items="${comentarios}">
                                <tr>
                                    <td><c:out value="${c.getIdcomentario()}"/></td>
                                    <td><c:out value="${c.getDescripcion()}"/></td>
                                    <td><c:out value="${c.getNombre()}"/></td>
                                    <td><a href="${pageContext.request.contextPath}/denunciarComServlet?id=${c.getIdcomentario()}">Denunciar</a></td>
                                </tr>
                            </c:forEach>
                        </table>   
                    </c:when>
                    <c:otherwise>
                        <div class="alert-success"><h3>No hay comentaros</h3></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
