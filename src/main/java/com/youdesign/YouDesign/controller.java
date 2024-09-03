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

    @GetMapping("/sobre_nosotros")
    public String verNosotros() {
        return "sobre_nosotros"; // Debe coincidir con el nombre del archivo HTML en la carpeta templates
    }
    @GetMapping("/productos")
    public String verProductos() {
        return "productos"; // Debe coincidir con el nombre del archivo HTML en la carpeta templates
    }
    @GetMapping("/contacto")
    public String verContacto() {
        return "contacto"; // Debe coincidir con el nombre del archivo HTML en la carpeta templates
    }
}
