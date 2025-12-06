# 🔢 Gestión de Turnos – Sistema Web con Servlets, JSP y JPA

## 📌 Descripción

Este proyecto implementa un **sistema de gestión de turnos** desarrollado en **Java**, utilizando **Jakarta EE (Servlets/JSP)**, **JPA/Hibernate**, **MySQL** y una capa de servicios que separa la lógica de negocio del controlador.

Funciones principales:

- Registrar **ciudadanos**.
- Registrar **turnos** vinculados a un ciudadano.
- Validar fechas y estados.
- Listar ciudadanos y turnos.
- Filtrar turnos por **estado** y **fecha**.
- Marcar turnos como **"Ya atendido"** desde la interfaz.
- Persistir toda la información en base de datos usando JPA.

---

## 🧱 Tecnologías utilizadas

- **Java 22**
- **Jakarta Servlet 6.0**
- **JPA / Hibernate**
- **MySQL / MariaDB**
- **Maven**
- **JSP + Bootstrap 5**

---

## 📁 Estructura del Proyecto
| Sección                         | Contenido                                                                                                                       |
| ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| **Nombre del proyecto**         | Gestión de Turnos                                                                                                               |
| **Descripción**                 | Sistema web para registrar ciudadanos y turnos, con CRUD, validaciones y persistencia en MySQL.                                 |
| **Tecnologías**                 | Java 22, Servlets 6.0, JSP, Maven, Hibernate/JPA, MySQL, Bootstrap 5                                                            |
| **Funcionalidades principales** | - Registrar ciudadanos <br> - Registrar turnos <br> - Filtrar turnos <br> - Actualizar estado <br> - Listar ciudadanos y turnos |
| **Estructura del proyecto**     | entities/, services/, servlets/, persistence/, JSP                                                                              |
| **Requisitos**                  | JDK 22, Tomcat 10+, MySQL 8, Maven                                                                                              |
| **Instalación rápida**          | 1. Clonar repo <br> 2. Crear DB <br> 3. Configurar `persistence.xml` <br> 4. `mvn package` <br> 5. Desplegar en Tomcat          |
| **Autores**                     | Leonardo de Oliveira, Sebastián Riveros, Sergio Gago, Ismael Peña                                                               |

---

## ⚙️ Requisitos

- **JDK 22**
- **Apache Tomcat 10+**
- **MySQL 8 o MariaDB**
- **Maven 3.8+**

---

## 🚀 Instalación y Ejecución

### 1️⃣ Clonar repositorio
```bash
git clone https://github.com/JS1305/GestionTurnos
2️⃣ Crear base de datos
sql
Copiar código
CREATE DATABASE turnos;
3️⃣ Configurar credenciales en persistence.xml
xml
Copiar código
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="tu_password"/>
4️⃣ Compilar
bash
Copiar código
mvn clean package
5️⃣ Desplegar en Tomcat
Copiar el archivo .war en la carpeta webapps/ de Tomcat.

Luego ingresar:

arduino
Copiar código
http://localhost:8080/GestionTurnos
🧩 Funcionalidades
👤 Gestión de Ciudadanos
Registrar ciudadanos.

Listar todos los ciudadanos.

Ver cuántos turnos tiene cada uno.

🕒 Gestión de Turnos
Crear turnos asociados a un ciudadano.

Validar fecha (no se permiten fechas pasadas).

Estado inicial y editable.

Identificador autoincremental (no ID, sino número de turno).

Filtros por:

Estado

Fecha

Actualizar estado → Ya atendido.

🧰 Ejemplo: Generación del identificador del turno
java
Copiar código
public int generarNuevoIdentificador() {
    Integer max = em.createQuery(
        "SELECT MAX(t.identificador) FROM Turno t", Integer.class
    ).getSingleResult();

    return (max == null) ? 1 : max + 1;
}
🖥️ Interfaz Web
Incluye páginas JSP con Bootstrap:

index.jsp

registroCiudadano.jsp

registroTurno.jsp

listarCiudadanos.jsp

listarTurnos.jsp

Con componentes reutilizados:

bash
Copiar código
partials/header.jsp
partials/footer.jsp
📝 Notas
La creación de tablas es automática (hibernate.hbm2ddl.auto=update).

Separación MVC básica:

Servlets → Controladores

Servicios → Lógica de negocio

JSP → Vistas

Proyecto ideal para practicar ecosistema Jakarta EE y JPA.

🤝 Autores
© 2025 — Sistema de Gestión de Turnos
Leonardo de Oliveira, Sebastián Riveros, Sergio Gago, Ismael Peña