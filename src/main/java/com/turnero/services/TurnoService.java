package com.turnero.services;

import com.turnero.entities.Turno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class TurnoService {

    private EntityManager em =
            Persistence.createEntityManagerFactory("TurneroPU").createEntityManager();

    public Turno crearTurno(Turno turno) {
        em.getTransaction().begin();
        em.persist(turno);
        em.getTransaction().commit();
        return turno;
    }

    public Turno obtenerTurno(Long id) {
        return em.find(Turno.class, id);
    }

    public List<Turno> obtenerTodos() {
        return em.createQuery("SELECT t FROM Turno t", Turno.class)
                .getResultList();
    }

    public Turno actualizarTurno(Long id, Turno turnoActualizado) {
        Turno turno = obtenerTurno(id);

        if (turno == null) {
            return null;
        }

        em.getTransaction().begin();

        turno.setEstado(turnoActualizado.getEstado());
        turno.setDescripcion(turnoActualizado.getDescripcion());
        turno.setFecha(turnoActualizado.getFecha());
        turno.setCiudadano(turnoActualizado.getCiudadano());

        em.getTransaction().commit();

        return turno;
    }

    public boolean eliminarTurno(Long id) {
        Turno turno = obtenerTurno(id);

        if (turno == null) {
            return false;
        }

        em.getTransaction().begin();
        em.remove(turno);
        em.getTransaction().commit();

        return true;
    }
}
