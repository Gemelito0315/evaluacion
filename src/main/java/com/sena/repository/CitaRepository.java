package com.sena.repository;

import com.sena.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    
    // Buscar citas por estado
    List<Cita> findByEstado(String estado);
    
    // Contar citas por estado
    long countByEstado(String estado);
    
    // Buscar citas por usuario (si necesitas esta funcionalidad)
    // List<Cita> findByUsuarioId(Long usuarioId);
    
    // Buscar citas por profesional (si necesitas esta funcionalidad)
    // List<Cita> findByProfesionalId(Long profesionalId);
}