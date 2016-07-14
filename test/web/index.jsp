<%-- 
    Document   : PagLogin
    Created on : 30/06/2016, 11:41:26 PM
    Author     : wilson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="source/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="source/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
    <script src="source/js/bootstrap.min.js" type="text/javascript"></script>
    <title>Pagina de Login</title>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">

                <a class="navbar-brand" href="#">CrowdFonding Guatemala</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <form class="navbar-form navbar-right" action="LoginServlet" method="POST">
                    <div class="form-group">
                        <input type="text" placeholder="Nickname" name="nik" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Contraseña" name="pw" class="form-control">
                    </div>
                    
                    <button type="submit" class="btn btn-success">Accesar</button>
                </form>
            </div><!--/.navbar-collapse -->
        </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <h1>CrowdFondign Guatemala</h1>
            <p>Aplicacion web para crear iniciativas, crear recompensas para dichas recompensas. Los inversores puede elegir una iniciativa que apoyar y escoger el premio que quiera.</p>
            <p><a class="btn btn-primary btn-lg" href="#" role="button">Leer mas &raquo;</a></p>
        </div>
    </div>

    <div class="container">
        <!-- Example row of columns -->
        <div class="row">
            <div class="col-md-4">
                <h2>Iniciativas</h2>
                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div>
            <div class="col-md-4">
                <h2>Recompensas</h2>
                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div>
            <div class="col-md-4">
                <h2>Donaciones</h2>
                <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
                <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
            </div>
        </div>

        <hr>
    </div> <!-- /container -->       

</body>
</html>
