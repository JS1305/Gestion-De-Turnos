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

@WebServlet("/registrar-turno")
public class CrearTurnoServlet extends HttpServlet {

    private CiudadanoService ciudadanoService = new CiudadanoService();
    private TurnoService turnoService = new TurnoService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // --- Obtener parámetros ---
            String estado = request.getParameter("estado");
            String descripcion = request.getParameter("descripcion");
            String fecha = request.getParameter("fecha");
            Long ciudadanoId = Long.parseLong(request.getParameter("ciudadano_id"));

            // --- Validaciones ---
            if (estado == null || fecha == null || ciudadanoId == null) {
                request.setAttribute("error", "Los campos obligatorios no fueron enviados.");
                request.getRequestDispatcher("form-turno").forward(request, response);
                return;
            }

            // --- Obtener ciudadano ---
            Ciudadano ciudadano = ciudadanoService.obtenerCiudadano(ciudadanoId);

            if (ciudadano == null) {
                request.setAttribute("error", "El ciudadano seleccionado no existe.");
                request.getRequestDispatcher("form-turno").forward(request, response);
                return;
            }

            // --- Generar identificador automático ---
            int identificador = turnoService.generarNuevoIdentificador();

            // --- Crear turno ---
            Turno nuevoTurno = new Turno(
                    identificador,
                    estado,
                    descripcion,
                    fecha,
                    ciudadano
            );

            turnoService.crearTurno(nuevoTurno);

            // --- Redirigir con éxito ---
            response.sendRedirect("form-turno?success=true");

        } catch (Exception e) {

            // Cualquier error inesperado
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar el turno: " + e.getMessage());
            request.getRequestDispatcher("form-turno").forward(request, response);
        }
    }
}