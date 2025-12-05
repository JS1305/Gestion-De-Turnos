package com.turnero.servlets;

import com.turnero.entities.Ciudadano;
import com.turnero.services.CiudadanoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/form-turno")
public class MostrarFormularioTurnoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CiudadanoService service = new CiudadanoService();
        List<Ciudadano> lista = service.obtenerTodos();

        request.setAttribute("listaCiudadanos", lista);

        request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
    }
}