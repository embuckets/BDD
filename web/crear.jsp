<%-- 
    Document   : crear
    Created on : 10/12/2018, 04:22:16 PM
    Author     : yaqic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="templates/head.jsp"></jsp:include>
        
        <title>SIVO Crear Encuesta</title>
        <script src="js/admin-crear.js"></script>         
    </head>
    <body>
        
        <jsp:include page="templates/header-admin.jsp"></jsp:include>

            <main>
                <h1>Crear Encuesta</h1>
                <div class="form-crear" id="container">
                    <form method="POST" id="form-crear" action="crear" onSubmit="return validar()">
                        <label class="form-crear-titulo" for="titulo">Titulo</label>
                        <input class="form-crear-input" type="text" name="titulo" required>
                        <label class="form-crear-titulo" for="desc">Descripción</label>
                        <textarea class="form-crear-input form-crear-input-area" name="desc" rows="5" form="form-crear" required></textarea>

                        <label class="form-crear-titulo" for="abre">Abre</label>
                        <label class="form-crear-titulo-inline" for="abre-date">Fecha</label>
                        <input id="abre-date" class="form-crear-date"type="date" name="abre-date" required>
                        <label class="form-crear-titulo-inline" for="abre-time">Hora</label>
                        <input id="abre-time" class="form-crear-date"type="time" name="abre-time" required>

                        <label class="form-crear-titulo" for="cierra">Cierra</label>
                        <label class="form-crear-titulo-inline" for="cierra-date">Fecha</label>
                        <input id="cierra-date" class="form-crear-date"type="date" name="cierra-date" required>
                        <label class="form-crear-titulo-inline" for="cierra-time">Hora</label>
                        <input id="cierra-time" class="form-crear-date"type="time" name="cierra-time" required>

                        <label class="form-crear-titulo" for="opciones">Opciones</label>
                        <input class="form-crear-input-opc" type="text" id="myInput">
                        <span onclick="newOption()" class="form-crear-new" >Agregar</span>
                        <div id="myOptions"></div>
                        <input class="card-button" type="submit" name="submit" value="Crear">
                    </form>
                </div>
            </main>

        <jsp:include page="templates/footer.jsp"></jsp:include>
    </body>
</html>
