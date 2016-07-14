<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">          
            <a class="navbar-brand" href="#">CrowdFonding Guatemala</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="usu.jsp">Inicio</a></li>
                <li><a href="${pageContext.request.contextPath}/lsIniciativasServlet?idusu=<%= usuarioActual.getIdusuario()%>">Mis iniciativas</a></li>
                <li><a href="misPatrocinios.jsp">Recompensas adquiridas</a></li>
                <li><a href="todasIniciativas.jsp">Todas las iniciativas</a></li>
                <li><a href="todosPatrocinios.jsp">Todas las compras</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="../navbar/">Hola</a></li>            
                <li class="active"><a href="cerrar.jsp"><span class="glyphicon glyphicon-user"></span> Salir</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<!-- fin navbar -->