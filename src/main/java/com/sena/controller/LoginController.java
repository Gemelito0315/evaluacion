package com.sena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    // @Autowired  // Comenta esta línea temporalmente
    // private UsuarioService usuarioService;
    
    @GetMapping("/")
    public String home() {
        return "login";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}