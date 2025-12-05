package com.turnero.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("turneroPU"); // coincide con persistence.xml

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
