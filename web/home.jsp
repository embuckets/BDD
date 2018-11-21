<%-- 
    Document   : newjsp
    Created on : 19/11/2018, 11:01:28 PM
    Author     : emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="templates/head.jsp"></jsp:include>
            <title>SIVO Home</title>
        </head>
        <body onload="requestEncuestas()">
        <jsp:include page="templates/header.jsp"></jsp:include>

            <h1>Hello ${rol}</h1>
        <h1>Matricula: ${matricula}</h1>
        <h1>Nombre: ${nombre}</h1>

        <p id="json"></p>

        <jsp:include page="templates/footer.jsp"></jsp:include>
        <script>
            function requestEncuestas() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        display(this.responseText);
                    }
                }
                ;
                xhttp.open("GET", "encuesta", true);
                xhttp.send();
            }

            function display(json) {
                var para = document.getElementById("json");
                var obj = JSON.parse(json);
                para.innerHTML = json;
                    
//                var index;
//                for (index in json){
//                    var encuesta = json[index];
//                    para += encuesta.idEncuesta + ", " + 
//                }
            }
        </script>
    </body>
</html>
