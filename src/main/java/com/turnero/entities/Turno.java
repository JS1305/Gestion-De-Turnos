package com.turnero.entities;

import jakarta.persistence.*;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int identificador; // Progresivo generado en TurnoService
    private String estado;     // "En espera" / "Ya atendido"
    private String descripcion;
    private String fecha;

    // Relación con Ciudadano
    @ManyToOne
    @JoinColumn(name = "ciudadano_id")
    private Ciudadano ciudadano;

    // Constructores
    public Turno() {}

    public Turno(String estado, String descripcion, String fecha, Ciudadano ciudadano) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ciudadano = ciudadano;
    }

    // Getters y setters
    public Long getId() { return id; }
    public int getIdentificador() { return identificador; }
    public void setIdentificador(int identificador) { this.identificador = identificador; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public Ciudadano getCiudadano() { return ciudadano; }
    public void setCiudadano(Ciudadano ciudadano) { this.ciudadano = ciudadano; }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", identificador=" + identificador +
                ", estado='" + estado + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", ciudadano=" + ciudadano +
                '}';
    }
}