package com.turnero.persistence;

import jakarta.persistence.*;

public class JpaUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("turneroPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}