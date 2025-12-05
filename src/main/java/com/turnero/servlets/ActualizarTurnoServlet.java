package com.turnero.servlets;

import com.turnero.entities.Turno;
import com.turnero.services.TurnoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
@WebServlet("/actualizar-Turnos")
public class ActualizarTurnoServlet extends HttpServlet {

    private TurnoService turnoService = new TurnoService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] ids = request.getParameterValues("marcarAtendido");

        if (ids != null) {
            for (String idStr : ids) {
                Long id = Long.valueOf(idStr);
                turnoService.actualizarEstado(id, "Ya atendido");
            }
        }

        // volver a cargar la lista
        request.setAttribute("listaTurnos", turnoService.obtenerTodos());
        request.getRequestDispatcher("listarTurnos.jsp").forward(request, response);
    }
}
