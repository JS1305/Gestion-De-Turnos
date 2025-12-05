package com.turnero.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int identificador;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private String fecha;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudadano_id", nullable = false)
    private Ciudadano ciudadano;
    public Turno() {}
    public Turno(int identificador, String estado, String descripcion, String fecha, Ciudadano ciudadano) {
        this.identificador = identificador;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.ciudadano = ciudadano;
    }
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
        return "Turno{id=" + id + ", identificador=" + identificador +
                ", estado='" + estado + "', descripcion='" + descripcion +
                "', fecha='" + fecha + "', ciudadano=" + (ciudadano != null ? ciudadano.getId() : null) + "}";
    }
}