<%@page import="west.ws.Subcategoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Pagina de usuario</title>
        <script src="source/misJS/dynamicoptionlist.js"></script>        
    </head>
    <body onLoad="initDynamicOptionLists()">
        <%@include file="/plantilla/header.jsp" %>
        <%@include file="/plantilla/navbar_usuario.jsp" %>






        <%            try {
                west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                west.ws.PruebaServicio port = service.getPruebaServicioPort();
                java.lang.String query = "";
                int resultado = -1;
                String boton = null;
                if ((boton = request.getParameter("operacion")) != null) {
                    String id = request.getParameter("id");
                    String nombre = request.getParameter("nombre");
                    String metae = request.getParameter("metae");
                    String metat = request.getParameter("metat");
                    int idusuario = usuarioActual.getIdusuario();
                    
                    String opSubcategoria = request.getParameter("subcategoriaa");
                    String arreglo[]=opSubcategoria.split("-");
                    String subcategoria=arreglo[0];
                    
                    if (boton.equalsIgnoreCase("Crear")) {
                        query = "INSERT INTO iniciativa(idiniciativa, \"nombreIni\", metaeconomica, metatiempo,\n"
                                + "idusuario, idsubcategoria, estado)\n"
                                + "VALUES (default, '" + nombre + "', " + metae + ", '" + metat + "', " + idusuario + ", \n"
                                + "            " + subcategoria + ", 0);";
                        resultado = port.queryWS(query);

                        if (resultado > 0) {
                            request.setAttribute("msj", "Se creo la iniciativa");
                        } else {
                            request.setAttribute("msj", "NO se creo la iniciativa");
                        }
                    } else if (boton.equalsIgnoreCase("Modificar")) {
                        query = "UPDATE iniciativa SET \"nombreIni\"='" + nombre + "', metaeconomica=" + metae + ", metatiempo='" + metat + "', \n"
                                + "idusuario=" + idusuario + ", idsubcategoria=" + subcategoria + ", estado=0\n"
                                + "WHERE idiniciativa=" + id + ";";
                        resultado = port.queryWS(query);

                        if (resultado > 0) {
                            request.setAttribute("msj", "Se modifico la iniciativa");
                        } else {
                            request.setAttribute("msj", "NO se modifico la iniciativa");
                        }
                    } else if (boton.equalsIgnoreCase("Eliminar")) {
                        query = "DELETE FROM iniciativa WHERE idiniciativa=" + id + ";";

                        resultado = port.queryWS(query);

                        if (resultado > 0) {
                            request.setAttribute("msj", "Se elimino la iniciativa");
                        } else {
                            request.setAttribute("msj", "NO se elimino la iniciativa");
                        }
                    }

                }

            } catch (Exception ex) {
                System.err.println("usu.jsp: una excepcion: " + ex + " //msj: " + ex.getMessage());
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
                            <input type="text" class="form-control" name="id" value="${iniciativa.getIdiniciativa()}" placeholder="Identificador de la iniciativa">
                        </div>
                    </div>            
                    <div class="form-group">
                        <label for="nombre" class="col-lg-2 control-label">Nombre</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="nombre" value="${iniciativa.getNombreIni()}" placeholder="Ingrese nombre de la iniciativa">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="metae" class="col-lg-2 control-label">Meta economica</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="metae" value="${iniciativa.getMetaeconomica()}" placeholder="Meta economica">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="metat" class="col-lg-2 control-label">Meta de Tiempo</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" name="metat" value="${iniciativa.getMetatiempo()}" placeholder="Meta de tiempo">
                        </div>
                    </div>   


                    <%
                        try {
                            west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
                            west.ws.PruebaServicio port = service.getPruebaServicioPort();
                            // TODO process result here
                            java.util.List<west.ws.Categoria> lcat = port.lsCategoriaWS();

                            String codigojavascript = "";
                            for (int i = 0; i < lcat.size(); i++) {
                                String aux = "";
                                List<Subcategoria> lsubcat = port.lsSubcategoriaWS(String.valueOf(lcat.get(i).getIdcategoria()));
                                String parte1 = "sub.forValue(\"" + lcat.get(i).getIdcategoria() + "\").addOptions(";
                                for (int j = 0; j < lsubcat.size(); j++) {
                                    aux = aux + "\"" + lsubcat.get(j).getIdsubcategoria() + "-" + lsubcat.get(j).getNombreSub() + "\",";
                                }
                                aux = aux.substring(0, aux.length() - 1);
                                codigojavascript = codigojavascript + parte1 + aux + ");\n";
                            }

                            pageContext.setAttribute("categorias", lcat);
                            pageContext.setAttribute("codigojavascript", codigojavascript);
                            System.out.println(codigojavascript);

                        } catch (Exception ex) {
                            System.err.println("usu.jsp: una excepcion: " + ex + " //msj: " + ex.getMessage());
                        }
                    %>
                    <div class="form-group">
                        <div class="col-lg-10">
                            <%-- ejemplo de como usar la libreria dynamicoptionlist.js
                            <script type="text/javascript">
                               var names = new DynamicOptionList();
                               names.addDependentFields("sex", "names");
                               names.forValue("boy").addOptions("Matt", "Bob", "Joe", "Bill", "John");
                               names.forValue("girl").addOptions("Jane", "Angie", "Jennifer", "Amy", "Sue");
                               names.forValue("boy").setDefaultOptions("Joe");
                            </script>
                            <select name="sex">
                               <option value="boy">Boy</option>
                               <option value="girl">Girl</option>
                            </select>
                            <select name="names">                        
                            </select>
                            --%>                            
                            <script type="text/javascript">
                                var sub = new DynamicOptionList();
                                sub.addDependentFields("categoriaa", "subcategoriaa");
                                ${pageScope.codigojavascript}
                            </script>
                            Categoria:                            
                            <select name="categoriaa">
                                <c:forEach items="${categorias}" var="cat">
                                    <option value="${cat.getIdcategoria()}">${cat.getNombreCat()}</option>
                                </c:forEach>
                            </select>
                            Subcategoria:                            
                            <select name="subcategoriaa">
                            </select>
                        </div>
                    </div>

                            <div class="row">.</div>
                            
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <input type="submit" class="btn btn-default" name="operacion" value="Crear">
                                <input type="submit" class="btn btn-default" name="operacion" value="Modificar">
                                <input type="submit" class="btn btn-default" name="operacion" value="Eliminar">                    
                                <input type="submit" class="btn btn-default" name="operacion" value="Cargar subcategorias">
                            </div>
                        </div>

                        <div class="alert alert-warning">
                            <c:out value="${msj}"/>
                        </div>



                </form>

            </div><!--fin del panel-body-->
        </div><!--fin del panel-->


            <%@include file="/plantilla/footer.jsp" %>
    </body>
</html>
