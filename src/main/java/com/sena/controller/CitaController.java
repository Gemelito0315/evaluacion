package com.sena.controller;

import com.sena.model.Cita;
import com.sena.model.Servicio;
import com.sena.model.Profesional;
import com.sena.model.Usuario;
import com.sena.service.CitaService;
import com.sena.service.ServicioService;
import com.sena.service.ProfesionalService;
import com.sena.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ProfesionalService profesionalService;

    @Autowired
    private UsuarioService usuarioService;

    // Página principal de citas
    @GetMapping
    public String mostrarCitas(Model model) {
        model.addAttribute("titulo", "Gestión de Citas");
        return "citas";
    }

    // Mostrar lista de citas
    @GetMapping("/lista")
    public String listarCitas(Model model) {
        List<Cita> citas = citaService.obtenerTodasCitas();
        model.addAttribute("citas", citas);
        return "citas/lista-citas";
    }

    // Mostrar formulario para crear cita
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaCita(Model model) {
        model.addAttribute("cita", new Cita());
        
        // Cargar datos para los select
        List<Servicio> servicios = servicioService.obtenerTodosServicios();
        List<Profesional> profesionales = profesionalService.obtenerTodosProfesionales();
        List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        
        model.addAttribute("servicios", servicios);
        model.addAttribute("profesionales", profesionales);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Nueva Cita");
        
        return "citas/form-cita";
    }

    // Guardar nueva cita
    @PostMapping("/guardar")
    public String guardarCita(@ModelAttribute Cita cita, RedirectAttributes redirectAttributes) {
        try {
            citaService.guardarCita(cita);
            redirectAttributes.addFlashAttribute("mensaje", "Cita guardada exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar la cita");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/citas/lista";
    }

    // Mostrar formulario para editar cita
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCita(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Cita cita = citaService.obtenerCitaPorId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));
            model.addAttribute("cita", cita);
            
            // Cargar datos para los select
            List<Servicio> servicios = servicioService.obtenerTodosServicios();
            List<Profesional> profesionales = profesionalService.obtenerTodosProfesionales();
            List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
            
            model.addAttribute("servicios", servicios);
            model.addAttribute("profesionales", profesionales);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("titulo", "Editar Cita");
            
            return "citas/form-cita";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Cita no encontrada");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            return "redirect:/citas/lista";
        }
    }

    // Eliminar cita
    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            citaService.eliminarCita(id);
            redirectAttributes.addFlashAttribute("mensaje", "Cita eliminada exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar la cita");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/citas/lista";
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Cita>> apiListarCitas() {
        return ResponseEntity.ok(citaService.obtenerTodasCitas());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<?> apiObtenerCita(@PathVariable Long id) {
        return citaService.obtenerCitaPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada"));
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<?> apiCrearCita(@RequestBody Cita cita) {
        try {
            Cita creada = citaService.guardarCita(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear la cita");
        }
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<?> apiActualizarCita(@PathVariable Long id, @RequestBody Cita cita) {
        return citaService.obtenerCitaPorId(id)
                .<ResponseEntity<?>>map(actual -> {
                    cita.setId(id);
                    Cita actualizada = citaService.guardarCita(cita);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada"));
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<?> apiEliminarCita(@PathVariable Long id) {
        try {
            citaService.eliminarCita(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
        }
    }

    @GetMapping("/api/estado/{estado}")
    @ResponseBody
    public ResponseEntity<List<Cita>> apiListarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(citaService.obtenerCitasPorEstado(estado));
    }
}