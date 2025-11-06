package com.sena.config;

import com.sena.model.Usuario;
import com.sena.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdmin(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "admin@local";
            if (!usuarioRepository.existsByEmail(adminEmail)) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol("ROLE_ADMIN");
                usuarioRepository.save(admin);
            }
        };
    }
}
