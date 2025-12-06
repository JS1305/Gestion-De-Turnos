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
@WebServlet("/listar-Turnos")
public class ListarTurnosServlet extends HttpServlet {

    private TurnoService turnoService = new TurnoService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String estado = request.getParameter("estado");
        String fecha = request.getParameter("fecha");

        List<Turno> listaTurnos = turnoService.obtenerTodos();

        List<Turno> listaFiltrada = listaTurnos.stream()
                .filter(t -> estado == null || estado.isBlank()
                        || t.getEstado().equalsIgnoreCase(estado))
                .filter(t -> fecha == null || fecha.isBlank()
                        || t.getFecha().equals(fecha))
                .sorted(Comparator.comparing(Turno::getIdentificador))
                .toList();

        request.setAttribute("listaTurnos", listaFiltrada);
        request.getRequestDispatcher("listarTurnos.jsp").forward(request, response);
    }
}
