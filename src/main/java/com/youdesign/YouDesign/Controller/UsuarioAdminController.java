package com.youdesign.YouDesign.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioAdminController {
    @GetMapping("admin/usuarios")
    public String usuario(Model model) {
        model.addAttribute("pageTitle", "Registro Usuarios");
        return "admin/usuarios";
    }
}
