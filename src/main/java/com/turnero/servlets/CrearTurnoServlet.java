package com.turnero.servlets;

import com.turnero.entities.Ciudadano;
import com.turnero.entities.Turno;
import com.turnero.persistence.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registrar-turno")
public class CrearTurnoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoger datos
        int identificador = Integer.parseInt(request.getParameter("identificador"));
        String estado = request.getParameter("estado");
        String descripcion = request.getParameter("descripcion");
        String fecha = request.getParameter("fecha");
        int ciudadano_id = Integer.parseInt(request.getParameter("ciudadano_id"));

        // Validar campos
        if (identificador <= 0 ||estado == null || estado.isBlank() || descripcion == null || descripcion.isBlank() ||
                fecha == null || fecha.isBlank() || ciudadano_id <= 0) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
            return;
        }

        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Ciudadano ciudadano = new Ciudadano();
            ciudadano.setId((long) ciudadano_id);
            Turno turno = new Turno(identificador, estado, descripcion, fecha, ciudadano);
            em.persist(turno);

            tx.commit();

            // Redirección con mensaje de éxito (GET)
            response.sendRedirect("registroTurno.jsp?success=true");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();

            request.setAttribute("error", "Error al registrar el turno: " + e.getMessage());
            request.getRequestDispatcher("registroTurno.jsp").forward(request, response);

        } finally {
            em.close();
        }
    }
}
