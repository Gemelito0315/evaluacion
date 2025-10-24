package com.sena.controller;

import com.sena.model.Usuario;
import com.sena.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }
    
    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario, 
                                 RedirectAttributes redirectAttributes) {
        try {
            // Guardar el usuario en la base de datos
            usuarioService.guardarUsuario(usuario);
            
            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("mensaje", 
                "¡Registro exitoso! Ahora puedes iniciar sesión.");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            
            return "redirect:/login";
            
        } catch (RuntimeException e) {
            // Manejar error (email duplicado)
            redirectAttributes.addFlashAttribute("mensaje", 
                "Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            redirectAttributes.addFlashAttribute("usuario", usuario);
            
            return "redirect:/registro";
        }
    }
}