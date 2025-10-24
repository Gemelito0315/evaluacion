package com.sena.repository;
import com.sena.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
}