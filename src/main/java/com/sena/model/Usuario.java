package com.sena.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(nullable = false, length = 255)
    private String password;
    
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Rol rol;
    
    // Constructores
    public Usuario() {
        this.fechaRegistro = LocalDateTime.now();
        this.rol = Rol.USUARIO;
    }
    
    public Usuario(String nombre, String email, String password) {
        this();
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}

enum Rol {
    ADMINISTRADOR, SUPERVISOR, USUARIO
}