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
    private Ciudadano ciudadano;
    @OneToMany(mappedBy = "ciudadano", cascade = CascadeType.ALL)
    private List<Turno> turnos = new ArrayList<>();

    // Constructores
    public Ciudadano() {}

    public Ciudadano(String nombre, String dni, Ciudadano ciudadano) {
        this.nombre = nombre;
        this.dni = dni;
        this.ciudadano= ciudadano;
    }

    // Getters y setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public List<Turno> getTurnos() { return turnos; }
    public void setTurnos(List<Turno> turnos) { this.turnos = turnos; }
    public Ciudadano getCiudadano() {return ciudadano;}
    public void setCiudadano(Ciudadano ciudadano) {this.ciudadano = ciudadano;}

    @Override
    public String toString() {
        return "Ciudadano{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", ciudadano=" + ciudadano +
                ", turnos=" + turnos +
                '}';
    }
}