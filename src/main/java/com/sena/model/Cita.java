package com.sena.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
    
    @Column(name = "estado", length = 255)
    private String estado;
    
    // Una Cita pertenece a un Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    // Una Cita tiene un Servicio
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;
    
    // Una Cita tiene un Profesional
    @ManyToOne
    @JoinColumn(name = "profesional_id")
    private Profesional profesional;

    
    

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Servicio getServicio() { return servicio; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
    public Profesional getProfesional() { return profesional; }
    public void setProfesional(Profesional profesional) { this.profesional = profesional; }
}