package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TerminosController {
    private final CategoriaRepository categoriaRepository;

    public TerminosController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/terminosycondiciones")
    public String terminosycondiciones(Model model) {
        model.addAttribute("pageTitle", "Terminos y Condiciones");
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        return "terminos_condiciones";
    }
}
