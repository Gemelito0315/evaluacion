package com.sena.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

import com.sena.model.Usuario;
import com.sena.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/login", "/registro", "/css/**", "/js/**", "/images/**", "/assets/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                // Rutas de creación/edición/eliminación: cualquier usuario autenticado
                .requestMatchers(HttpMethod.GET,
                        "/servicios/nuevo", "/servicios/editar/**", "/servicios/eliminar/**",
                        "/profesionales/nuevo", "/profesionales/editar/**", "/profesionales/eliminar/**",
                        "/usuarios/nuevo", "/usuarios/editar/**", "/usuarios/eliminar/**",
                        "/citas/nueva", "/citas/editar/**", "/citas/eliminar/**"
                ).authenticated()
                .requestMatchers(HttpMethod.POST,
                        "/servicios/guardar", "/profesionales/guardar", "/usuarios/guardar", "/citas/guardar"
                ).authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/citas", true)
            )
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll())
            .httpBasic(httpBasic -> {});
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> {
            Usuario u = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            UserDetails details = User
                .withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRol() != null ? u.getRol().replace("ROLE_", "") : "USER")
                .build();
            return details;
        };
    }
}
