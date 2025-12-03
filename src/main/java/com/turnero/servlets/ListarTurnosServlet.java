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

@WebServlet("/listar-turnos")
public class ListarTurnosServlet extends HttpServlet {

    private TurnoService turnoService = new TurnoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Obtener turnos de DB
        List<Turno> listaTurnos = turnoService.obtenerTodos()
                .stream()
                //Ordenar turnos por identificador
                .sorted(Comparator.comparing(Turno::getIdentificador))
                .toList();

        request.setAttribute("turnos", listaTurnos);
        request.getRequestDispatcher("listarTurnos.jsp").forward(request, response);

    }
}
