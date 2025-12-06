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

    private CiudadanoService ciudadanoService = new CiudadanoService();
    private TurnoService turnoService = new TurnoService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //  Obtener parámetros
            String estado = request.getParameter("estado");
            String descripcion = request.getParameter("descripcion");
            String fecha = request.getParameter("fecha");
            String ciudadanoIdStr = request.getParameter("ciudadano_id");

            //  Validaciones básicas
            if (estado == null || estado.isBlank() ||
                    fecha == null || fecha.isBlank() ||
                    ciudadanoIdStr == null || ciudadanoIdStr.isBlank()) {

                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
                return;
            }

            Long ciudadanoId;
            try {
                ciudadanoId = Long.parseLong(ciudadanoIdStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Ciudadano inválido.");
                request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
                return;
            }

            //  Validación de límite de turnos
            int maxTurnosPendientes = 3;
            List<Turno> pendientes = turnoService.obtenerTodos().stream()
                    .filter(t -> t.getCiudadano().getId().equals(ciudadanoId))
                    .filter(t -> t.getEstado().equals("En espera"))
                    .toList();

            if (pendientes.size() >= maxTurnosPendientes) {
                request.setAttribute("error",
                        "Máximo " + maxTurnosPendientes + " turnos en estado 'En espera' por ciudadano. " +
                                "Por favor, actualice estado de turnos actuales o espere a que sean resueltos.");
                request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
                return;
            }

            //  Validación de fecha
            LocalDate fechaTurno;
            try {
                fechaTurno = LocalDate.parse(fecha); // formato yyyy-MM-dd
            } catch (DateTimeParseException e) {
                request.setAttribute("error", "La fecha ingresada no es válida.");
                request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
                return;
            }

            if (fechaTurno.isBefore(LocalDate.now())) {
                request.setAttribute("error", "La fecha no puede ser anterior a la actual.");
                request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
                return;
            }

            //  Verificar existencia del ciudadano
            Ciudadano ciudadano = ciudadanoService.obtenerCiudadano(ciudadanoId);
            if (ciudadano == null) {
                request.setAttribute("error", "El ciudadano seleccionado no existe.");
                request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
                return;
            }

            //  Crear turno
            int identificador = turnoService.generarNuevoIdentificador();
            Turno nuevoTurno = new Turno(identificador, estado, descripcion, fechaTurno.toString(), ciudadano);
            turnoService.crearTurno(nuevoTurno);

            //  Redirigir con éxito
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/form-turno?success=true&id=" + nuevoTurno.getId());

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado al registrar el turno: " + e.getMessage());
            request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
        }
    }
}
