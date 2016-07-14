<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion de recompensas</title>
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>    
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_usuario.jsp" %>


        <jsp:scriptlet>            pageContext.setAttribute("idiniciativa", request.getParameter("idinici"));
            pageContext.setAttribute("nominiciativa", request.getParameter("nomini"));
        </jsp:scriptlet>

        <%            try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                java.lang.String query = "";
                int resultado = -1;
                String boton = null;
                if ((boton = request.getParameter("operacion")) != null) {
                    String idiniciativa = request.getParameter("idini");
                    String id = request.getParameter("id");
                    String descripcion = request.getParameter("descripcion");
                    String minimo = request.getParameter("minimo");
                    String cantidad = request.getParameter("cantidad");
                    System.out.println("cantidad: " + cantidad
                    );
                    if (cantidad.isEmpty()) {
                        cantidad = "-1";
                    }

                    if (boton.equalsIgnoreCase("Crear")) {
                        query = "INSERT INTO recompensa(idrecompensa, descripcion, p_minimo, cantidad, iniciativa, fisico)\n"
                                + "VALUES (default, '" + descripcion + "', " + minimo + ", " + cantidad + ", " + idiniciativa + ", 0);";
                        resultado = port.queryWS(query);

                        if (resultado > 0) {
                            request.setAttribute("msj", "Se creo la recompensa");
                        } else {
                            request.setAttribute("msj", "NO se creo la recompensa");
                        }
                    } else if (boton.equalsIgnoreCase("Modificar")) {
                        query = "UPDATE recompensa SET descripcion='" + descripcion + "', p_minimo=" + minimo + ", \n"
                                + "cantidad=" + cantidad + ", iniciativa=" + idiniciativa + ", fisico=0\n"
                                + "WHERE idrecompensa=" + id + ";";
                        resultado = port.queryWS(query);

                        if (resultado > 0) {
                            request.setAttribute("msj", "Se modifico la recompensa");
                        } else {
                            request.setAttribute("msj", "NO se modifico la recompensa");
                        }
                    } else if (boton.equalsIgnoreCase("Eliminar")) {
                        query = "DELETE FROM recompensa WHERE idrecompensa=" + id + ";";

                        resultado = port.queryWS(query);

                        if (resultado > 0) {
                            request.setAttribute("msj", "Se elimino la recompensa");
                        } else {
                            request.setAttribute("msj", "NO se elimino la recompensa");
                        }
                    }

                }

            } catch (Exception ex) {
                System.err.println("recompensa.jsp: una excepcion: " + ex + " //msj: " + ex.getMessage());
            }
        %>


        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Gestionar recompensa para la iniciativa &emsp14;<span style="font-size: 23px;"><strong>${pageScope.nominiciativa}</strong></span></h3>
            </div>
            <div class="panel-body">

                <form class="form-horizontal" method="post">
                    <div class="form-group">
                        <label for="idini" class="col-lg-2 control-label">Id Iniciativa</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" value="${pageScope.idiniciativa}" name="idini"  placeholder="Identificador de iniciativa">
                        </div>
                    </div>            
                    <div class="form-group">
                        <label for="id" class="col-lg-2 control-label">Id Recompensa</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="id"  placeholder="Identificador de la recompensa">
                        </div>
                    </div>            
                    <div class="form-group">
                        <label for="descripcion" class="col-lg-2 control-label">Descripcion</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="descripcion"  placeholder="Ingrese descripcion de la recompensa">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="minimo" class="col-lg-2 control-label">Precio minimo</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="minimo"  placeholder="Ingrese el precio minimo">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cantidad" class="col-lg-2 control-label">Cantidad</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="cantidad" placeholder="Si es recompensa ilimitada no poner nada aqui" >
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" class="btn btn-default" name="operacion" value="Crear">
                            <input type="submit" class="btn btn-default" name="operacion" value="Modificar">
                            <input type="submit" class="btn btn-default" name="operacion" value="Eliminar">
                        </div>
                    </div>

                    <div class="alert alert-warning">
                        <c:out value="${msj}"/>
                    </div>

                </form>

            </div><!--fin del panel-body-->
        </div><!--fin del panel-->

        <%
            try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                // TODO initialize WS operation arguments here
                java.lang.String idUsuOIni = request.getParameter("idinici");
                java.lang.String modo = "porIniciativa";
                // TODO process result here
                java.util.List<west.ws.Recompensa> result = port.lsRecompensaWS(idUsuOIni, modo);
                request.setAttribute("recompensas", result);
            } catch (Exception ex) {
                System.err.println("recompensa.jsp: una excepcion: " + ex + " //msj: " + ex.getMessage());
            }
        %>


        <c:choose>
            <c:when test="${not empty recompensas}">
                <h3>Recompensas de esta iniciativa</h3>
                <table class="table table-striped">            
                    <th>Id Recompensa</th>
                    <th>Descripcion</th>
                    <th>Precio minimo</th>
                    <th>Cantidad</th>
                    <th>Nombre iniciativa</th>
                        <c:forEach items="${requestScope.recompensas}" var="recom">
                        <tr>
                            <td>${recom.getIdrecompensa()}</td>
                            <td>${recom.getDescripcion()}</td>
                            <td>${recom.getPMinimo()}</td>
                            <c:choose>
                                <c:when test="${recom.getCantidad()>0}">
                                    <td>${recom.getCantidad()}</td>                                
                                </c:when>
                                <c:otherwise>
                                    <td>Ilimitado</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${recom.getNombreIni()}</td>
                        </tr>
                    </c:forEach>                 
                </table>
            </c:when>
            <c:otherwise>
                <div class="alert-success"><h3>Aun No hay recompensas creadas</h3></div>
            </c:otherwise>
        </c:choose>

        <%@include file="/plantilla/footer.jsp" %>
    </body>
</html>
