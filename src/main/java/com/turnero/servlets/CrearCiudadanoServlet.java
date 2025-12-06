package com.turnero.servlets;

import com.turnero.entities.Ciudadano;
import com.turnero.services.CiudadanoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/crear-ciudadano")
public class CrearCiudadanoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");

        // Validación básica
        if (nombre == null || nombre.isBlank() || dni == null || dni.isBlank()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("registroCiudadano.jsp").forward(request, response);
            return;
        }

        //Recibir datos formulario de registro de ciudadano
        // y llama CiudadanoService para guardarlos
        CiudadanoService service = new CiudadanoService();
        try {
            Ciudadano ciudadano = new Ciudadano(nombre, dni);
            service.crearCiudadano(ciudadano); // Guardamos usando el servicio
            response.sendRedirect("registroCiudadano.jsp?success=true");
        } catch (Exception e) {
            request.setAttribute("error", "Error al registrar ciudadano: " + e.getMessage());
            request.getRequestDispatcher("registroCiudadano.jsp").forward(request, response);
        }

    }
}