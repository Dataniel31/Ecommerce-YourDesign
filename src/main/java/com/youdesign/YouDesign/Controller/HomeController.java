package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Entity.Marca;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import com.youdesign.YouDesign.Repository.MarcaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    public HomeController(MarcaRepository marcaRepository, CategoriaRepository categoriaRepository) {
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Inicio");
        List<Marca> marca = marcaRepository.findAll();
        model.addAttribute("marca", marca);
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        return "index";
    }
}
