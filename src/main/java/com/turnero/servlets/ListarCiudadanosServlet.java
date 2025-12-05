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

@WebServlet("/listar-ciudadanos")
public class ListarCiudadanosServlet extends HttpServlet {

    private CiudadanoService ciudadanoService = new CiudadanoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener todos los ciudadanos
        List<Ciudadano> lista = ciudadanoService.obtenerTodos();

        // Enviarlos al JSP
        request.setAttribute("listaCiudadanos", lista);

        // Forward a la vista
        request.getRequestDispatcher("listarCiudadanos.jsp").forward(request, response);
    }
}