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
    
    // Guardar usuario
    public Usuario guardarUsuario(Usuario usuario) {
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        return usuarioRepository.save(usuario);
    }
    
    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }
    
    // Buscar usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    // Buscar usuario por email
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    
    // Eliminar usuario
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    // Verificar si existe usuario por email
    public boolean existeUsuarioConEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}