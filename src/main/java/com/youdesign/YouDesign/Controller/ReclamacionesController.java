package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReclamacionesController {
    private final CategoriaRepository categoriaRepository;

    public ReclamacionesController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/lreclamaciones")
    public String MostrarReclamaciones(Model model) {
        model.addAttribute("pageTitle", "Libro de Reclamaciones");
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        return "libro_reclamaciones";
    }
}
