<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.turnero.entities.Ciudadano" %>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Turno</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="partials/header.jsp" %>

<div class="container my-5">


    <h2 class="mb-4">Registrar Turno</h2>

    <%
    String success = request.getParameter("success");
    String error = (String) request.getAttribute("error");
    List<Ciudadano> lista = (List<Ciudadano>) request.getAttribute("listaCiudadanos");
    String idTurno = request.getParameter("id");
    %>

    <% if ("true".equals(success)) { %>
        <div class="alert alert-success">
            Turno registrado correctamente.
            <% if (idTurno != null) { %>
                <br>📌 ID del turno generado: <strong><%= idTurno %></strong>
            <% } %>
        </div>
    <% } %>

    <% if (error != null) { %>
        <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <form action="registrar-turno" method="post" class="card p-4 shadow">

        <!-- Estado -->
        <div class="mb-3">
            <label class="form-label">Estado</label>
            <select name="estado" class="form-select" required>
                <option value="En espera">En espera</option>
                <option value="Ya atendido">Ya atendido</option>
            </select>
        </div>

        <!-- Descripción -->
        <div class="mb-3">
            <label class="form-label">Descripción</label>
            <textarea name="descripcion" class="form-control"></textarea>
        </div>

        <!-- Fecha -->
        <div class="mb-3">
            <label class="form-label">Fecha</label>
            <input type="date" name="fecha" class="form-control" required min="<%= java.time.LocalDate.now() %>">
        </div>

        <!-- Ciudadano -->
        <div class="mb-3">
            <label class="form-label">Ciudadano</label>
            <select name="ciudadano_id" class="form-select" required>
                <option value="" disabled selected>Seleccione...</option>
                <% if (lista != null) {
                for (Ciudadano c : lista) { %>
                <option value="<%= c.getId() %>">
                    <%= c.getNombre() %> - DNI <%= c.getDni() %>
                </option>
                <% }} %>
            </select>

            <% if (lista == null || lista.isEmpty()) { %>
            <p class="text-danger mt-2">⚠ No hay ciudadanos registrados.</p>
            <% } %>
        </div>

        <button class="btn btn-primary">Registrar Turno</button>
    </form>

</div>

<%@ include file="partials/footer.jsp" %>

<!-- Bootstrap JS opcional -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>