<%-- 
    Document   : index
    Created on : 01-04-2021, 1:08:09
    Author     : rivaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FlujoCash</title>
        <link rel="stylesheet" href="Style/StyleIndex.css">
        
    </head>
    <body>
        
        <form class="form-registro" action="Validate.do" method="post">
            <h2>Ingreso CashFlow</h2>
            <input class="control" type="text" name="rut" placeholder="Ingrese Rut ">
            <input  class="control" type="password" name="pass" placeholder="Ingrese Password ">
            <input class="boton"  type="submit" name="btniniciar" value="Iniciar Sesion ">
            
        </form>
    </body>
</html>
