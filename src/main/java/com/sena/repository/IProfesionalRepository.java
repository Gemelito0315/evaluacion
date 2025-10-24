package com.sena.repository;
import com.sena.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfesionalRepository extends JpaRepository<Profesional, Long> {
}