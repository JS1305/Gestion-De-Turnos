<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Página Principal</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- CSS propio -->
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<!-- Header -->
<%@ include file="partials/header.jsp" %>

<main class="container my-5">
    <h2 class="text-center">Página Principal</h2>
    <p class="text-center">Bienvenido al sistema de gestión de turnos.</p>
</main>

<!-- Footer -->
<%@ include file="partials/footer.jsp" %>

<!-- Bootstrap JS opcional -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
