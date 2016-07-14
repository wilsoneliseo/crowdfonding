<%@page import="west.ws.Usuario"%>
<%@page import="west.ws.TipoUsuario"%>

<div class="container" style="padding-top: 40px">
    <div class="page-header">
        <% Usuario usuarioActual = (Usuario) session.getAttribute("actualSesionUsuario");
            String str_tipoUsuario = "Desconocido";

            if (usuarioActual.getTipo() == TipoUsuario.ADMINISTRADOR) {
                str_tipoUsuario = TipoUsuario.ADMINISTRADOR.name();
            }

            if (usuarioActual.getTipo() == TipoUsuario.USUARIO) {
                str_tipoUsuario = TipoUsuario.USUARIO.name();
            }

        %>

        <h1><%= str_tipoUsuario%> <%= usuarioActual.getNombre()%>.</h1>
        <p>  Nickname: <%= usuarioActual.getNickname()%>   &emsp14; &emsp14; &emsp14; Codigo: <%= usuarioActual.getIdusuario()%> </p>

    </div>