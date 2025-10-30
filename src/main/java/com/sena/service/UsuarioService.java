package com.sena.service;

import com.sena.model.Usuario;
import com.sena.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // Método para el REGISTRO
    public Usuario guardarUsuario(Usuario usuario) {
        // Verificar si el email ya existe
        if (existeEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        return usuarioRepository.save(usuario);
    }
    
    // Método para verificar si existe email
    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
    
    // Método para el LOGIN
    public Usuario validarCredenciales(String email, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Verificar contraseña
            if (password.equals(usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }
    
    // Método para listar usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }
    
    // Método para buscar por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}