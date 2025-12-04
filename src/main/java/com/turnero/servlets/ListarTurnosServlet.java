package com.turnero.servlets;

import com.turnero.entities.Turno;
import com.turnero.services.TurnoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listaTurnos")
public class ListarTurnosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TurnoService servicio = new TurnoService();
        try {
            List<Turno> turnos = servicio.obtenerTodos();
            request.setAttribute("listaTurnos", turnos);
            request.getRequestDispatcher("/listarTurnos.jsp").forward(request, response);
        } finally {
            servicio.cerrar();
        }
    }
}
