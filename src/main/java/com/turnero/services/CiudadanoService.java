package com.turnero.services;

import com.turnero.entities.Ciudadano;
import com.turnero.persistence.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

 //Servicio encargado de la lógica de negocio para la entidad Ciudadano.
 //Implementa operaciones CRUD usando JPA.
 // Mantiene la persistencia separada del Servlet para seguir buenas prácticas.

public class CiudadanoService {

     // Crea y persiste un nuevo ciudadano en la base de datos.
    public Ciudadano crearCiudadano(Ciudadano ciudadano) {
        // Obtener EntityManager (gestiona la conexión con la BD)
        EntityManager em = JpaUtil.getEntityManager();
        // Iniciar transacción
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Persistir el ciudadano en la BD
            em.persist(ciudadano);
            tx.commit(); // Confirmar la transacción
            return ciudadano;
        } catch (Exception e) {
            // Si hay error, revertir cambios
            if (tx.isActive()) tx.rollback();
            throw e; // Lanzar la excepción para que el servlet pueda manejarla
        } finally {
            em.close(); // Cerrar EntityManager para liberar recursos
        }
    }

     // Obtiene un ciudadano por su ID
    public Ciudadano obtenerCiudadano(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // find busca la entidad por su clave primaria
            return em.find(Ciudadano.class, id);
        } finally {
            em.close();
        }
    }

     //Devuelve todos los ciudadanos de la base de datos
    public List<Ciudadano> obtenerTodos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // JPQL: "SELECT c FROM Ciudadano c" obtiene todos los objetos Ciudadano
            TypedQuery<Ciudadano> query = em.createQuery("SELECT c FROM Ciudadano c", Ciudadano.class);
            return query.getResultList(); // Devuelve la lista de resultados
        } finally {
            em.close();
        }
    }

     //Actualiza un ciudadano existente
    public Ciudadano actualizarCiudadano(Long id, Ciudadano actualizado) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Buscar el ciudadano existente
            Ciudadano ciudadano = em.find(Ciudadano.class, id);
            if (ciudadano == null) return null;

            // Actualizar los campos
            ciudadano.setNombre(actualizado.getNombre());
            ciudadano.setDni(actualizado.getDni());

            tx.commit();
            return ciudadano;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

     //Elimina un ciudadano de la base de datos
    public boolean eliminarCiudadano(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            // Buscar el ciudadano a eliminar
            Ciudadano ciudadano = em.find(Ciudadano.class, id);
            if (ciudadano != null) {
                em.remove(ciudadano); // Eliminar
                tx.commit();
                return true;
            }
            return false; // No se encontró el ciudadano
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
