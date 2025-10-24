package com.sena.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 255)
    private String nombre;
    
    @Column(name = "descripcion", length = 255)
    private String descripcion;
    
    @Column(name = "duracion", length = 255)
    private String duracion;
    
    @Column(name = "precio")
    private Double precio;
    
    // Un Servicio es ofrecido por un Profesional
    @ManyToOne
    @JoinColumn(name = "profesional_id")
    private Profesional profesional;
    
    // Un Servicio puede estar en muchas Citas
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    private List<Cita> citas = new ArrayList<>();

  

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public Profesional getProfesional() { return profesional; }
    public void setProfesional(Profesional profesional) { this.profesional = profesional; }
    public List<Cita> getCitas() { return citas; }
    public void setCitas(List<Cita> citas) { this.citas = citas; }
}