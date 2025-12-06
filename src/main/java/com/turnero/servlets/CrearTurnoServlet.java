package com.turnero.servlets;

import com.turnero.entities.Ciudadano;
import com.turnero.entities.Turno;
import com.turnero.services.CiudadanoService;
import com.turnero.services.TurnoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/registrar-turno")
public class CrearTurnoServlet extends HttpServlet {

    private final CiudadanoService ciudadanoService = new CiudadanoService();
    private final TurnoService turnoService = new TurnoService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String estado = request.getParameter("estado");
            String descripcion = request.getParameter("descripcion");
            String fecha = request.getParameter("fecha");
            String ciudadanoIdStr = request.getParameter("ciudadano_id");

            // Validaciones básicas
            if (estado == null || estado.isBlank() ||
                    fecha == null || fecha.isBlank() ||
                    ciudadanoIdStr == null || ciudadanoIdStr.isBlank()) {

                mostrarError(request, response, "Todos los campos son obligatorios.");
                return;
            }

            if (!validarCiudadanoId(ciudadanoIdStr)) {
                mostrarError(request, response, "Ciudadano inválido.");
                return;
            }

            Long ciudadanoId = Long.parseLong(ciudadanoIdStr);

            if (limiteTurnosSuperado(ciudadanoId)) {
                mostrarError(request, response,
                        "Máximo 3 turnos en estado 'En espera' por ciudadano. " +
                                "Por favor, actualice estado de turnos actuales o espere a que sean resueltos.");
                return;
            }

            LocalDate fechaTurno;
            try {
                fechaTurno = LocalDate.parse(fecha);
            } catch (DateTimeParseException e) {
                mostrarError(request, response, "La fecha ingresada no es válida.");
                return;
            }

            if (fechaTurno.isBefore(LocalDate.now())) {
                mostrarError(request, response, "La fecha no puede ser anterior a la actual.");
                return;
            }

            Ciudadano ciudadano = ciudadanoService.obtenerCiudadano(ciudadanoId);
            if (ciudadano == null) {
                mostrarError(request, response, "El ciudadano seleccionado no existe.");
                return;
            }

            // Crear turno
            int identificador = turnoService.generarNuevoIdentificador();
            Turno nuevoTurno = new Turno(identificador, estado, descripcion, fechaTurno.toString(), ciudadano);
            turnoService.crearTurno(nuevoTurno);

            response.sendRedirect(request.getContextPath() + "/form-turno?success=true&id=" + nuevoTurno.getId());

        } catch (Exception e) {
            e.printStackTrace();
            mostrarError(request, response, "Error inesperado al registrar el turno: " + e.getMessage());
        }
    }

    private void mostrarError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {
        request.setAttribute("error", mensaje);
        request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
    }

    private boolean validarCiudadanoId(String ciudadanoIdStr) {
        try {
            Long.parseLong(ciudadanoIdStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean limiteTurnosSuperado(Long ciudadanoId) {
        int maxTurnosPendientes = 3;
        List<Turno> pendientes = turnoService.obtenerTodos().stream()
                .filter(t -> t.getCiudadano().getId().equals(ciudadanoId))
                .filter(t -> t.getEstado().equals("En espera"))
                .toList();
        return pendientes.size() >= maxTurnosPendientes;
    }
}
