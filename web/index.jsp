<%-- 
    Document   : index
    Created on : 19/11/2018, 03:31:58 PM
    Author     : emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String root = getServletContext().getContextPath(); %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="templates/head.jsp"></jsp:include>
    <title>SIVO</title>
</head>

<body>
    <header>
        <div class="topnav">
            <a class="active center" href=<%=root%>>SIVO</a>
        </div>
    </header>
    <main>
        <form action="login" method="POST">
            <div class="form-log">
                <label for="uname"><b>Matrícula</b></label>
                <input class="form-input" type="text" placeholder="" name="uname" required>

                <label for="psw"><b>Contraseña</b></label>
                <input class="form-input" type="password" placeholder="" name="psw" required>

                <button class="form-submit" type="submit">Entrar</button>
        </form>
    </main>
    <jsp:include page="templates/footer.jsp"></jsp:include>
</body>

</html>