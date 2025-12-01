<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Turno</title>
</head>
<body>

<!-- Header -->
<%@ include file="partials/header.jsp" %>

<main>
    <h2>Registrar un nuevo Turno</h2>

    <form action="registrar-turno" method="post">

        <label for="identificador">Identificador:</label>
        <input type="number" id="identificador" name="identificador" required>
        <br><br>

        <label for="estado">Estado:</label>
        <select id="estado" name="estado" required>
            <option value="pendiente">Pendiente</option>
            <option value="completado">Completado</option>
            <option value="cancelado">Cancelado</option>
        </select>
        <br><br>

        <label for="descripcion">Descripción:</label><br>
        <textarea id="descripcion" name="descripcion" rows="4" cols="40"></textarea>
        <br><br>

        <label for="fecha">Fecha:</label>
        <input type="date" id="fecha" name="fecha" required>
        <br><br>

        <label for="ciudadano">ID del Ciudadano:</label>
        <input type="number" id="ciudadano" name="ciudadano_id" required>
        <br><br>

        <button type="submit">Registrar Turno</button>
    </form>

</main>

<!-- Footer -->
<%@ include file="partials/footer.jsp" %>

</body>
</html>
