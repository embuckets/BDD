<%-- 
    Document   : header
    Created on : 19/11/2018, 06:49:24 PM
    Author     : emilio
--%>
<%@page import="dominio.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        String userName = usuario.getNombre();
        String home = getServletContext().getContextPath() + "/home";
%>  
<header>
    <div class="topnav">
        <a class="active left" href=<%=home%>>SIVO</a>
        <!-- <a href="#news">News</a>
        <a href="#contact">Contact</a>
        <a href="#about">About</a> -->
        <div class="dropdown right">
            <button class="dropbtn"><%=userName%>
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-content">
                <a href="logout"><i class="fa fa-power-off"></i> Salir</a>
            </div>
        </div>
    </div>
</header>