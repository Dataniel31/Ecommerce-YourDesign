package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PoliticasPrivController {
    private final CategoriaRepository categoriaRepository;

    public PoliticasPrivController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/politicaprivacidad")
    public String politicaprivacidad(Model model) {
        model.addAttribute("pageTitle", "Politicas de Privacidad");
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        return "politica_privacidad";
    }
}
