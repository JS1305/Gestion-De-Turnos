<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Turno</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- CSS propio -->
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Header -->
<%@ include file="partials/header.jsp" %>

<div class="container my-5">
    <h2 class="text-center mb-4">Registrar un nuevo Turno</h2>

    <!-- Mensaje de éxito -->
    <%
        String success = request.getParameter("success");
        String idTurno = request.getParameter("id");
        String error = (String) request.getAttribute("error");
        if ("true".equals(success) && idTurno != null) {
    %>
        <div class="alert alert-success text-center">
            Turno registrado correctamente: <strong>#<%= idTurno %></strong>
        </div>
    <%
        }
        if (error != null) {
    %>
        <div class="alert alert-danger text-center">
            <%= error %>
        </div>
    <%
        }
    %>

    <form action="registrar-turno" method="post" class="mt-4">
        <!-- Identificador ahora oculto, generado automáticamente -->
        <input type="hidden" name="identificador">

        <div class="mb-3">
            <label for="estado" class="form-label">Estado:</label>
            <select id="estado" name="estado" class="form-select" required>
                <option value="pendiente">Pendiente</option>
                <option value="completado">Completado</option>
                <option value="cancelado">Cancelado</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción:</label>
            <textarea id="descripcion" name="descripcion" rows="4" class="form-control"></textarea>
        </div>

        <div class="mb-3">
            <label for="fecha" class="form-label">Fecha:</label>
            <input type="date" id="fecha" name="fecha" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="ciudadano" class="form-label">ID del Ciudadano:</label>
            <input type="number" id="ciudadano" name="ciudadano_id" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary">Registrar Turno</button>
    </form>
</div>

<!-- Footer -->
<%@ include file="partials/footer.jsp" %>

<!-- Bootstrap JS opcional -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
