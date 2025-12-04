<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Ciudadano</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- CSS propio -->
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Header -->
<%@ include file="partials/header.jsp" %>

<main class="container my-5">
    <h2 class="text-center mb-4">Registrar Ciudadano</h2>

    <!-- Mensajes de éxito/error -->
    <%
        String success = request.getParameter("success");
        String error = (String) request.getAttribute("error");
        if ("true".equals(success)) {
    %>
        <div class="alert alert-success text-center">Ciudadano registrado correctamente.</div>
    <%
        }
        if (error != null) {
    %>
        <div class="alert alert-danger text-center"><%= error %></div>
    <%
        }
    %>

    <form action="crear-ciudadano" method="post" class="mx-auto" style="max-width: 500px;">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" id="nombre" name="nombre" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="dni" class="form-label">DNI</label>
            <input type="text" id="dni" name="dni" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Registrar</button>
    </form>

    <div class="text-center mt-3">
        <a href="listaCiudadanos" class="btn btn-secondary">Ver lista de ciudadanos</a>
    </div>
</main>

<!-- Footer -->
<%@ include file="partials/footer.jsp" %>

<!-- Bootstrap JS opcional -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
