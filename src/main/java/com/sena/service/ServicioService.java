package com.sena.service;

import com.sena.model.Servicio;
import com.sena.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {
    
    @Autowired
    private ServicioRepository servicioRepository;
    
    public List<Servicio> obtenerTodosServicios() {
        return servicioRepository.findAll();
    }
    
    public Servicio guardarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }
    
    public Optional<Servicio> obtenerServicioPorId(Long id) {
        return servicioRepository.findById(id);
    }
    
    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }
}