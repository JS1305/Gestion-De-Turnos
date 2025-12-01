package com.turnero.pruebas;

import com.turnero.persistece.JpaUtil;

public class TestBD {
    public static void main(String[] args) {
        JpaUtil.getEntityManager();
        System.out.println("✓ JPA inicializado. Las tablas deberían estar creadas.");
    }
}
