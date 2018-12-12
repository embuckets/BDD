<%-- 
    Document   : resultados
    Created on : 4/12/2018, 04:37:04 PM
    Author     : emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String home = getServletContext().getContextPath() + "/home";
    String rol = (String) request.getSession().getAttribute("rol");
%>  
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="templates/head.jsp"></jsp:include>
            <script src="js/encuesta.js"></script>
            <script src="js/resultados.js"></script>
            <title>SIVO Resultados</title>
        </head>

        <body onload="requestResultados()">
        <% if (rol.equalsIgnoreCase("admin")) { %>
        <jsp:include page="templates/header-admin.jsp"></jsp:include>
        <% } else if (rol.equalsIgnoreCase("alumno")) {%>
        <jsp:include page="templates/header.jsp"></jsp:include>
        <% }%>

        <main>
            <h1>Resultados Encuesta</h1>
            <div class="card justify">
                <h3 id="titulo" class="card-title"></h3>
                <p id="desc" class="card-text"></p>
                <p id="abre" class="text-muted"></p>
                <p id="cierra" class="text-muted"></p>
                <table class="table-res">
                    <thead>
                        <tr>
                            <th>Opcion</th>
                            <th>Votos</th>
                        </tr>
                    </thead>
                    <tbody class="table-body" id="resultados">

                    </tbody>
                </table>
                <a class="return-btn" href="<%=home%>">Regresar</a>
            </div>
        </main>

        <jsp:include page="templates/footer.jsp"></jsp:include>
    </body>

</html>