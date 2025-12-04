<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.turnero.entities.Turno" %>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Turnos</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- CSS propio -->
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<%@ include file="partials/header.jsp" %>

<div class="container my-5">
    <h2 class="text-center mb-4">Listado de Turnos</h2>

    <%
        List<Turno> lista = (List<Turno>) request.getAttribute("listaTurnos");
    %>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Estado</th>
                <th>Descripción</th>
                <th>Fecha</th>
                <th>ID Ciudadano</th>
            </tr>
        </thead>
        <tbody>
        <%
        if (lista != null && !lista.isEmpty()) {
            for (Turno t : lista) {
        %>
            <tr>
                <td><%= t.getIdentificador() %></td>
                <td><%= t.getEstado() %></td>
                <td><%= t.getDescripcion() %></td>
                <td><%= t.getFecha() %></td>
                <td><%= t.getCiudadano().getId() %></td>
            </tr>
        <%
            }
        } else {
        %>
            <tr>
                <td colspan="5" class="text-center">No hay turnos para mostrar</td>
            </tr>
        <%
        }
        %>
        </tbody>
    </table>
</div>

<%@ include file="partials/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
