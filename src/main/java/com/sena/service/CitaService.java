package com.sena.service;

import com.sena.model.Cita;
import com.sena.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    
    @Autowired
    private CitaRepository citaRepository;
    
    public List<Cita> obtenerTodasCitas() {
        return citaRepository.findAll();
    }
    
    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }
    
    public Optional<Cita> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id);
    }
    
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
    
    // Método adicional para buscar citas por estado
    public List<Cita> obtenerCitasPorEstado(String estado) {
        return citaRepository.findByEstado(estado);
    }
    
    // Método adicional para contar citas por estado
    public long contarCitasPorEstado(String estado) {
        return citaRepository.countByEstado(estado);
    }
}