<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.turnero.entities.Turno" %>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Turnos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="partials/header.jsp" %>

<div class="container my-5">

    <h2 class="mb-4">Listado de Turnos</h2>

    <%
    String estadoFiltro = request.getParameter("estado");
    String fechaFiltro = request.getParameter("fecha");
    List<Turno> lista = (List<Turno>) request.getAttribute("listaTurnos");
    %>

    <!-- Filtros -->
        <form class="row g-3 mb-4" method="get" action="listar-Turnos">
            <div class="col-md-4">
                <label for="estado" class="form-label">Estado</label>
                <select id="estado" name="estado" class="form-select">
                    <option value="" <%= (estadoFiltro == null || estadoFiltro.isBlank()) ? "selected" : "" %>>Todos</option>
                    <option value="En espera" <%= "En espera".equals(estadoFiltro) ? "selected" : "" %>>En espera</option>
                    <option value="Ya atendido" <%= "Ya atendido".equals(estadoFiltro) ? "selected" : "" %>>Ya atendido</option>
                </select>
            </div>

            <div class="col-md-4">
                <label for="fecha" class="form-label">Fecha</label>
                <input type="date" id="fecha" name="fecha" class="form-control"
                       value="<%= (fechaFiltro != null ? fechaFiltro : "") %>">
            </div>

            <div class="col-md-4 d-flex align-items-end">
                <button type="submit" class="btn btn-primary me-2">Filtrar</button>
                <a href="listar-Turnos" class="btn btn-secondary">Limpiar</a>
            </div>
        </form>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Identificador</th>
            <th>Estado</th>
            <th>Descripción</th>
            <th>Fecha</th>
            <th>ID Ciudadano</th>
        </tr>
        </thead>
        <tbody>
        <% if (lista != null && !lista.isEmpty()) {
        for (Turno t : lista) { %>
        <tr>
            <td><%= t.getIdentificador() %></td>
            <td><%= t.getEstado() %></td>
            <td><%= t.getDescripcion() %></td>
            <td><%= t.getFecha() %></td>
            <td><%= t.getCiudadano().getId() %></td>
        </tr>
        <%   }
        } else { %>
        <tr>
            <td colspan="5" class="text-center">No hay turnos registrados.</td>
        </tr>
        <% } %>
        </tbody>
    </table>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>