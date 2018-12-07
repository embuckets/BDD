<%-- 
    Document   : home-admin
    Created on : 7/12/2018, 10:25:32 AM
    Author     : emilio
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="templates/head.jsp"></jsp:include>
            <title>SIVO Home</title>
            <script src="js/home-admin.js"></script>
        </head>
        <body onload="requestEncuestas()">
        <jsp:include page="templates/header-admin.jsp"></jsp:include>

            <div class="range" id="range">
                <label class="date-range" for="abre-date">Abre</label>
                <input id="abre-date" class="date-range-input" type="date" name="abre-date">
                <label class="date-range" for="cierra-date">Cierra</label>
                <input id="cierra-date" class="date-range-input" type="date" name="cierra-date">
                <button class="range-btn" onclick="requestEncuestas()" >Ver</button>
            </div>
            <div id="container" class="encuestas grid-container">
            </div>

        <jsp:include page="templates/footer.jsp"></jsp:include>
        <script>
        </script>
        <script>
            var abre = new Date()
            var cierra = new Date();
            abre.setDate(abre.getDate() - 7);
            cierra.setDate(cierra.getDate() + 7);
            document.getElementById("abre-date").value = abre.toISOString().split('T')[0];
            document.getElementById("cierra-date").value = cierra.toISOString().split('T')[0];
        </script>
    </body>
</html>
