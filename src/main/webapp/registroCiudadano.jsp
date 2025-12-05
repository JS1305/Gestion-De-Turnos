<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Ciudadano</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="partials/header.jsp" %>

<div class="container my-5">

    <h2 class="mb-4">Registrar Ciudadano</h2>

    <%
    String success = request.getParameter("success");
    String error = (String) request.getAttribute("error");
    %>

    <% if ("true".equals(success)) { %>
    <div class="alert alert-success">Ciudadano registrado correctamente.</div>
    <% } %>

    <% if (error != null) { %>
    <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <form action="crear-ciudadano" method="post" class="card p-4 shadow">

        <div class="mb-3">
            <label class="form-label">Nombre</label>
            <input type="text" name="nombre" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label">DNI</label>
            <input type="text" name="dni" class="form-control" required>
        </div>

        <button class="btn btn-primary">Registrar</button>
    </form>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>