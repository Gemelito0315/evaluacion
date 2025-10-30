package com.sena.service;

import com.sena.model.Profesional;
import com.sena.repository.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesionalService {
    
    @Autowired
    private ProfesionalRepository profesionalRepository;
    
    public List<Profesional> obtenerTodosProfesionales() {
        return profesionalRepository.findAll();
    }
    
    public Profesional guardarProfesional(Profesional profesional) {
        return profesionalRepository.save(profesional);
    }
    
    public Optional<Profesional> obtenerProfesionalPorId(Long id) {
        return profesionalRepository.findById(id);
    }
    
    public void eliminarProfesional(Long id) {
        profesionalRepository.deleteById(id);
    }
}