package com.youdesign.YouDesign;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class controller {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Inicio");
        return "index";
    }
    @GetMapping("/login")
    public String login( ) {
        return "login";
    }

    @GetMapping("/lreclamaciones")
    public String lreclamaciones(Model model) {
        model.addAttribute("pageTitle", "Libro de Reclamaciones");
        return "libro_reclamaciones";
    }

    @GetMapping("/terminosycondiciones")
    public String terminosycondiciones(Model model) {
        model.addAttribute("pageTitle", "Terminos y Condiciones");
        return "terminos_condiciones";
    }

    @GetMapping("/politicaprivacidad")
    public String politicaprivacidad(Model model) {
        model.addAttribute("pageTitle", "Politicas de Privacidad");
        return "politica_privacidad";
    }

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("pageTitle", "Sobre Nosotros");
        return "sobre_nosotros";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("pageTitle", "Contactanos");
        return "contacto";
    }
}
