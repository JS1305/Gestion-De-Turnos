package com.turnero.services;

import com.turnero.entities.Ciudadano;
import com.turnero.persistence.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.function.Function;

public class CiudadanoService {

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

    // ---------------- Validación DNI ----------------
    private boolean validarDni(String dni) {
        if (dni == null || !dni.matches("^[0-9]{8}[A-Za-z]$")) return false;
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraCorrecta = letras.charAt(numero % 23);
        return Character.toUpperCase(dni.charAt(8)) == letraCorrecta;
    }

    // ---------------- CRUD ----------------

    public Ciudadano crearCiudadano(Ciudadano ciudadano) {
        if (!validarDni(ciudadano.getDni())) throw new IllegalArgumentException("DNI inválido");
        return ejecutarTransaccion(em -> {
            em.persist(ciudadano);
            return ciudadano;
        });
    }

    public Ciudadano obtenerCiudadano(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Ciudadano.class, id);
        } finally {
            em.close();
        }
    }

    public List<Ciudadano> obtenerTodos() {
        return ejecutarConsulta("SELECT c FROM Ciudadano c", Ciudadano.class);
    }

    public Ciudadano actualizarCiudadano(Long id, Ciudadano actualizado) {
        if (!validarDni(actualizado.getDni())) throw new IllegalArgumentException("DNI inválido");
        return ejecutarTransaccion(em -> {
            Ciudadano ciudadano = em.find(Ciudadano.class, id);
            if (ciudadano == null) return null;
            ciudadano.setNombre(actualizado.getNombre());
            ciudadano.setDni(actualizado.getDni());
            return ciudadano;
        });
    }

    public boolean eliminarCiudadano(Long id) {
        return ejecutarTransaccion(em -> {
            Ciudadano ciudadano = em.find(Ciudadano.class, id);
            if (ciudadano != null) {
                em.remove(ciudadano);
                return true;
            }
            return false;
        });
    }
}
