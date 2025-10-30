package com.sena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sena.model.Usuario;
import com.sena.service.UsuarioService;

@Controller
public class LoginController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/")
    public String home() {
        return "login";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String username,  // El usuario ingresa email como username
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {
        
        // Validar credenciales
        Usuario usuario = usuarioService.validarCredenciales(username, password);
        
        if (usuario != null) {
            // Login exitoso - redirigir a la página de citas
            redirectAttributes.addFlashAttribute("mensaje", "¡Bienvenido " + usuario.getNombre() + "!");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            return "redirect:/citas";
        } else {
            // Login fallido
            redirectAttributes.addFlashAttribute("mensaje", "Email o contraseña incorrectos");
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            return "redirect:/login";
        }
    }
}