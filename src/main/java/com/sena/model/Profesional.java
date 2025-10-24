package com.sena.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesional")
public class Profesional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "especialidad", length = 255)
    private String especialidad;
    
    @Column(name = "horario_disponible")
    private LocalDateTime horarioDisponible;
    
    // Un Profesional es un Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    // Un Profesional puede tener muchas Citas
    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Cita> citas = new ArrayList<>();
    
    // Un Profesional puede ofrecer muchos Servicios
    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL)
    private List<Servicio> servicios = new ArrayList<>();

   

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public LocalDateTime getHorarioDisponible() { return horarioDisponible; }
    public void setHorarioDisponible(LocalDateTime horarioDisponible) { this.horarioDisponible = horarioDisponible; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public List<Cita> getCitas() { return citas; }
    public void setCitas(List<Cita> citas) { this.citas = citas; }
    public List<Servicio> getServicios() { return servicios; }
    public void setServicios(List<Servicio> servicios) { this.servicios = servicios; }
}