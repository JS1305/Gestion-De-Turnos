package com.turnero.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ciudadano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String dni;

    // Relación con Turnos
    @OneToMany(mappedBy = "ciudadano", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turno> turnos = new ArrayList<>();

    // Constructores
    public Ciudadano() {}

    public Ciudadano(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    // Getters y setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public List<Turno> getTurnos() { return turnos; }
    public void setTurnos(List<Turno> turnos) { this.turnos = turnos; }

    public void addTurno(Turno turno) {
        turnos.add(turno);
        turno.setCiudadano(this);
    }

    public void removeTurno(Turno turno) {
        turnos.remove(turno);
        turno.setCiudadano(null);
    }

    @Override
    public String toString() {
        return "Ciudadano{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", turnos=" + turnos +
                '}';
    }
}