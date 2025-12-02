package com.turnero.servlets;

import com.turnero.entities.Ciudadano;
import com.turnero.persistence.JpaUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ciudadano ciudadano = new Ciudadano(nombre, dni);
            em.persist(ciudadano);

            tx.commit();

            // Redirección con mensaje de éxito (GET)
            response.sendRedirect("registroCiudadano.jsp?success=true");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

            request.setAttribute("error", "Error al registrar ciudadano: " + e.getMessage());
            request.getRequestDispatcher("registroCiudadano.jsp").forward(request, response);

        } finally {
            em.close();
        }
    }
}