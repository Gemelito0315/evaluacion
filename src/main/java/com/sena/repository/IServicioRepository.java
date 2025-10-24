package com.sena.repository;
import com.sena.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServicioRepository extends JpaRepository<Servicio, Long> {
}