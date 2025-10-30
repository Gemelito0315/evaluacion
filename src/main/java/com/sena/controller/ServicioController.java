package com.sena.controller;

import com.sena.model.Servicio;
import com.sena.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    // Mostrar lista de servicios
    @GetMapping("/lista")
    public String listarServicios(Model model) {
        List<Servicio> servicios = servicioService.obtenerTodosServicios();
        model.addAttribute("servicios", servicios);
        return "servicios/lista-servicios";
    }

    // Mostrar formulario para crear servicio
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoServicio(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("titulo", "Nuevo Servicio");
        return "servicios/form-servicio";
    }

    // Guardar nuevo servicio
    @PostMapping("/guardar")
    public String guardarServicio(@ModelAttribute Servicio servicio, RedirectAttributes redirectAttributes) {
        try {
            servicioService.guardarServicio(servicio);
            redirectAttributes.addFlashAttribute("mensaje", "Servicio guardado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar el servicio");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/servicios/lista";
    }

    // Mostrar formulario para editar servicio
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarServicio(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Servicio servicio = servicioService.obtenerServicioPorId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado"));
            model.addAttribute("servicio", servicio);
            model.addAttribute("titulo", "Editar Servicio");
            return "servicios/form-servicio";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Servicio no encontrado");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            return "redirect:/servicios/lista";
        }
    }

    // Eliminar servicio
    @GetMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            servicioService.eliminarServicio(id);
            redirectAttributes.addFlashAttribute("mensaje", "Servicio eliminado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar el servicio");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/servicios/lista";
    }
}