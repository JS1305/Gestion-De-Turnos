<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Ciudadano</title>
</head>
<body>

<%@ include file="partials/header.jsp" %>

<main>
    <h2>Registrar ciudadano</h2>

    <!-- Mensaje de éxito -->
    <%
        String success = request.getParameter("success");
        String error = (String) request.getAttribute("error");
        if ("true".equals(success)) {
    %>
        <p style="color: green;">Ciudadano registrado correctamente.</p>
    <%
        }
        if (error != null) {
    %>
        <p style="color: red;"><%= error %></p>
    <%
        }
    %>

    <form action="crear-ciudadano" method="post">
        <label for="nombre">Nombre:</label><br>
        <input type="text" id="nombre" name="nombre" required>
        <br><br>

        <label for="dni">DNI:</label><br>
        <input type="text" id="dni" name="dni" required>
        <br><br>

        <button type="submit">Registrar</button>
    </form>

    <br>
    <a href="listaCiudadanos">Ver lista de ciudadanos</a>
</main>

<%@ include file="partials/footer.jsp" %>

</body>
</html>
