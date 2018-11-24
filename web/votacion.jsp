<%-- 
    Document   : votacion
    Created on : 21/11/2018, 06:22:51 PM
    Author     : emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="templates/head.jsp"></jsp:include>
    <script src="js/votacion.js"></script>
    <title>SIVO Votacion</title>
</head>

<body onload="requestOpciones()">
    <jsp:include page="templates/header.jsp"></jsp:include>

    <div class="form-voto">
        <form id="form-voto" action="votar" method="POST"></form>
    </div>

    <jsp:include page="templates/footer.jsp"></jsp:include>
</body>

</html>