package com.turnero.services;

import com.turnero.entities.Turno;
import com.turnero.persistence.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.function.Function;

public class TurnoService {

    // ---------------- Transacción genérica ----------------
    private <T> T ejecutarTransaccion(Function<EntityManager, T> operacion) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T resultado = operacion.apply(em);
            tx.commit();
            return resultado;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // ---------------- Consulta genérica ----------------
    private <T> List<T> ejecutarConsulta(String jpql, Class<T> clase) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<T> query = em.createQuery(jpql, clase);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // ---------------- CRUD ----------------

    public Turno crearTurno(Turno turno) {
        return ejecutarTransaccion(em -> {
            em.persist(turno);
            return turno;
        });
    }

    public Turno obtenerTurno(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    public List<Turno> obtenerTodos() {
        return ejecutarConsulta("SELECT t FROM Turno t", Turno.class);
    }

    public Turno actualizarTurno(Long id, Turno actualizado) {
        return ejecutarTransaccion(em -> {
            Turno turno = em.find(Turno.class, id);
            if (turno == null) return null;
            turno.setEstado(actualizado.getEstado());
            turno.setDescripcion(actualizado.getDescripcion());
            turno.setFecha(actualizado.getFecha());
            turno.setCiudadano(actualizado.getCiudadano());
            return turno;
        });
    }

    public Turno actualizarEstado(Long id, String estado) {
        return ejecutarTransaccion(em -> {
            Turno turno = em.find(Turno.class, id);
            if (turno == null) return null;
            turno.setEstado(estado);
            return turno;
        });
    }

    public boolean eliminarTurno(Long id) {
        return ejecutarTransaccion(em -> {
            Turno turno = em.find(Turno.class, id);
            if (turno != null) {
                em.remove(turno);
                return true;
            }
            return false;
        });
    }

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
}
