<%-- 
    Document   : eleccion
    Created on : 13-04-2021, 0:01:02
    Author     : Santo Tomáss
--%>

<%@page import="model.Privilegio"%>
<%@page import="dao.Dao_privilegio"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link rel="stylesheet" href="Style/StyleNewjsp.css">
    <body>
        
        <%

            Usuario u = (Usuario) session.getAttribute("user");

            if (u == null) {
                response.sendRedirect("Error.jsp");
            } else {

                Dao_privilegio pv = new Dao_privilegio();
                Privilegio p = pv.getPrivilegio(u.getPrivilegio());

               

            }
        %>
       
       
        
       
        
        <header class="header">
		<div class="container">
		<div class="btn-menu">
			<label for="btn-menu">☰</label>
		</div>
			<div class="logo">
				<h1>Bienvenido señor/a: <%= u.getNombre()%></h1>
			</div>
			<nav class="menu">
				<a href="#">Inicio</a>
				<a href="#">Nosotros</a>
				<a href="#">Blog</a>
				<a href="#">Contacto</a>
			</nav>
		</div>
	</header>
	<div class="capa"></div>
<!--	--------------->
<input type="checkbox" id="btn-menu">
<div class="container-menu">
	<div class="cont-menu">
		<nav>
			<a href="finanzas.jsp">Ver mis Flujos</a>
			<a href="menu.jsp">Crear Nuevo Flujo</a>
			<a href="cerrarSesion.do">Cerrar Sesion</a>
			
		</nav>
		<label for="btn-menu">✖️</label>
	</div>
</div>
    </body>
</html>
