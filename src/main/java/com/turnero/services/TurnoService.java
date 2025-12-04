package com.turnero.services;

import com.turnero.entities.Turno;
import com.turnero.persistence.JpaUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class TurnoService {

    private final EntityManager em;

    public TurnoService() {
        this.em = JpaUtil.getEntityManager();
    }

    // Obtener todos los turnos ordenados por identificador
    public List<Turno> obtenerTodos() {
        return em.createQuery("SELECT t FROM Turno t ORDER BY t.identificador", Turno.class)
                .getResultList();
    }

    // Cerrar EntityManager
    public void cerrar() {
        if (em.isOpen()) em.close();
    }
}
