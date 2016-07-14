<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">          
            <a class="navbar-brand" href="#">CrowdFonding Guatemala</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="adm.jsp">Inicio</a></li>
                <li><a href="${pageContext.request.contextPath}/lsUsuariosServlet">Todos los usuarios</a></li>
                <li><a href="${pageContext.request.contextPath}/lsDenunComIniciativasServlet">Denuncias de comentarios</a></li>
                <li><a href="${pageContext.request.contextPath}/lsDenunIni">Denuncias de iniciativas</a></li>
                <li><a href="#contact">Contacto</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="admReporte.jsp">Reportes</a></li>            
                <li class="active"><a href="cerrar.jsp"><span class="glyphicon glyphicon-user"></span> Salir</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

