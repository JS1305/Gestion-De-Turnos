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

        // Obtener turnos desde la BD y ordenarlos
        List<Turno> listaTurnos = turnoService.obtenerTodos()
                .stream()
                .sorted(Comparator.comparing(Turno::getIdentificador))
                .toList();

        // Atributo correcto
        request.setAttribute("listaTurnos", listaTurnos);

        // Enviar a JSP
        request.getRequestDispatcher("listarTurnos.jsp").forward(request, response);
    }
}
