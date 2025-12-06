package com.turnero.services;

import com.turnero.entities.Turno;
import com.turnero.persistence.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.Comparator;
import java.util.List;

public class TurnoService {

    // Crear y guardar un turno
    public Turno crearTurno(Turno turno) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(turno);
            tx.commit();
            return turno;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Obtener todos los turnos
    public List<Turno> obtenerTodos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Turno> query = em.createQuery("SELECT t FROM Turno t", Turno.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Actualizar estado de un turno
    public Turno actualizarEstado(Long id, String estado) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Turno turno = em.find(Turno.class, id);
            if (turno != null) {
                turno.setEstado(estado);
                tx.commit();
                return turno;
            }
            return null;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Obtener el siguiente identificador automáticamente
    public int generarNuevoIdentificador() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Integer max = em.createQuery(
                    "SELECT MAX(t.identificador) FROM Turno t", Integer.class
            ).getSingleResult();
            return (max == null) ? 1 : max + 1;
        } finally {
            em.close();
        }
    }

    // Filtrar turnos por estado y fecha
    public List<Turno> obtenerFiltrados(String estado, String fecha) {
        return obtenerTodos().stream()
                .filter(t -> estado == null || estado.isBlank() || t.getEstado().equalsIgnoreCase(estado))
                .filter(t -> fecha == null || fecha.isBlank() || t.getFecha().equals(fecha))
                .sorted(Comparator.comparing(Turno::getIdentificador))
                .toList();
    }
}
