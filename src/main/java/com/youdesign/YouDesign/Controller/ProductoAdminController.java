package com.youdesign.YouDesign.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoAdminController {
    @GetMapping("admin/productos")
    public String producto(Model model) {
        model.addAttribute("pageTitle", "Registro Productos");
        return "admin/productos";
    }
}
