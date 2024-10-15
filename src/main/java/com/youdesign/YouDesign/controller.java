package com.youdesign.YouDesign;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class controller {

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

    @GetMapping("/producto")
    public String producto(Model model) {
        model.addAttribute("pageTitle", "Productos");
        return "producto";
    }
    @GetMapping("/cat-accesorios")
    public String accesorios(Model model) {
        model.addAttribute("pageTitle", "Productos");
        return "cat-accesorios";
    }
    @GetMapping("/cat-botines")
    public String botines(Model model) {
        model.addAttribute("pageTitle", "Productos");
        return "cat-botines";
    }
    @GetMapping("/cat-hombre")
    public String hombre(Model model) {
        model.addAttribute("pageTitle", "Productos");
        return "cat-hombre";
    }
    @GetMapping("/cat-infantil")
    public String mujer(Model model) {
        model.addAttribute("pageTitle", "Productos");
        return "cat-infantil";
    }
    @GetMapping("/cat-mujer")
    public String zapatillas(Model model) {
        model.addAttribute("pageTitle", "Productos");
        return "cat-mujer";
    }
    @GetMapping("/cat-zapatillas")
    public String infantil(Model model) {
        model.addAttribute("pageTitle", "Productos");
        return "cat-zapatillas";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("pageTitle", "Contactanos");
        return "contacto";
    }

    @GetMapping("/adlogin")
    public String adlogin(Model model) {
        model.addAttribute("pageTitle", "Login - Dashboard");
        return "admin/adlogin";
    }

}
