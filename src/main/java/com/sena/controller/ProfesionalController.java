package com.sena.controller;

import com.sena.model.Profesional;
import com.sena.model.Usuario;
import com.sena.service.ProfesionalService;
import com.sena.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/profesionales")
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar lista de profesionales
    @GetMapping("/lista")
    public String listarProfesionales(Model model) {
        List<Profesional> profesionales = profesionalService.obtenerTodosProfesionales();
        model.addAttribute("profesionales", profesionales);
        return "profesionales/lista-profesionales";
    }

    // Mostrar formulario para crear profesional
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProfesional(Model model) {
        model.addAttribute("profesional", new Profesional());
        // Cargar lista de usuarios para asignar como profesionales
        List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Nuevo Profesional");
        return "profesionales/form-profesional";
    }

    // Guardar nuevo profesional
    @PostMapping("/guardar")
    public String guardarProfesional(@ModelAttribute Profesional profesional, RedirectAttributes redirectAttributes) {
        try {
            profesionalService.guardarProfesional(profesional);
            redirectAttributes.addFlashAttribute("mensaje", "Profesional guardado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al guardar el profesional");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/profesionales/lista";
    }

    // Mostrar formulario para editar profesional
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProfesional(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Profesional profesional = profesionalService.obtenerProfesionalPorId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado"));
            model.addAttribute("profesional", profesional);
            // Cargar lista de usuarios para asignar como profesionales
            List<Usuario> usuarios = usuarioService.obtenerTodosUsuarios();
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("titulo", "Editar Profesional");
            return "profesionales/form-profesional";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Profesional no encontrado");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            return "redirect:/profesionales/lista";
        }
    }

    // Eliminar profesional
    @GetMapping("/eliminar/{id}")
    public String eliminarProfesional(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            profesionalService.eliminarProfesional(id);
            redirectAttributes.addFlashAttribute("mensaje", "Profesional eliminado exitosamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar el profesional");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/profesionales/lista";
    }
}