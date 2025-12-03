<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.turnero.entities.Turno" %>

<html>
<head>
    <title>Listado de Turnos</title>
</head>

    <body>

    <!-- Header -->
    <%@ include file="partials/header.jsp" %>

    <h2>Listado de Turnos</h2>

    <% List<Turno> lista = (List<Turno>) request.getAttribute("listaTurnos"); %>

    <table>
        <thead>
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
        if (lista != null) {
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
            <td colspan="5">No hay turnos para mostrar</td>
        </tr>
        <%
        }
        %>
        </tbody>
    </table>

    <!-- Footer -->
    <%@ include file="partials/footer.jsp" %>

    </body>
</html>