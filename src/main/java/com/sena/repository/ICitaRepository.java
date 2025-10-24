package com.sena.repository;
import com.sena.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICitaRepository extends JpaRepository<Cita, Long> {
}