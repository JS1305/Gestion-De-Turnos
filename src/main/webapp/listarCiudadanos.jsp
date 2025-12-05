<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.turnero.entities.Ciudadano" %>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Ciudadanos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="partials/header.jsp" %>

<div class="container my-5">

    <h2 class="mb-4">Listado de Ciudadanos</h2>

    <%
    List<Ciudadano> lista = (List<Ciudadano>) request.getAttribute("listaCiudadanos");
    %>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>DNI</th>
            <th>Cantidad de Turnos</th>
        </tr>
        </thead>

        <tbody>
        <% if (lista != null && !lista.isEmpty()) {
        for (Ciudadano c : lista) { %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getNombre() %></td>
            <td><%= c.getDni() %></td>
            <td><%= c.getTurnos().size() %></td>
        </tr>
        <%   }
        } else { %>
        <tr>
            <td colspan="4" class="text-center">No hay ciudadanos registrados.</td>
        </tr>
        <% } %>
        </tbody>
    </table>

</div>

<%@ include file="partials/footer.jsp" %>

</body>
</html>