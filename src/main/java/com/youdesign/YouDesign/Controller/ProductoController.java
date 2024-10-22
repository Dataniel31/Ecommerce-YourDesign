package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductoController {
    private final CategoriaRepository categoriaRepository;

    public ProductoController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/productos")
    public String politicaprivacidad(Model model) {
        model.addAttribute("pageTitle", "Productos");
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        return "list-producto";
    }
    @GetMapping("/plantilla")
    public String listarCategoriasPlantilla(Model model){
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
       return "plantilla";
    }
}
