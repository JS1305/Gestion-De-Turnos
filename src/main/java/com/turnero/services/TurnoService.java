package com.turnero.services;

import com.turnero.entities.Ciudadano;
import com.turnero.entities.Turno;
import com.turnero.persistence.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

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

    // Obtener un turno por ID
    public Turno obtenerTurno(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Turno.class, id);
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

    // Actualizar un turno existente
    public Turno actualizarTurno(Long id, Turno actualizado) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Turno turno = em.find(Turno.class, id);
            if (turno == null) return null;

            turno.setEstado(actualizado.getEstado());
            turno.setDescripcion(actualizado.getDescripcion());
            turno.setFecha(actualizado.getFecha());
            turno.setCiudadano(actualizado.getCiudadano());

            tx.commit();
            return turno;

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        } finally {
            em.close();
        }
    }

    // Actualizar estado de un turno
    public Turno actualizarEstado(Long id, String estado){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Turno turno = em.find(Turno.class, id);
            if (turno == null) return null;

            turno.setEstado(estado);

            tx.commit();
            return turno;

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;

        } finally {
            em.close();
        }
    }

    // Eliminar un turno
    public boolean eliminarTurno(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Turno turno = em.find(Turno.class, id);

            if (turno != null) {
                em.remove(turno);
                tx.commit();
                return true;
            }
            return false;

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
}