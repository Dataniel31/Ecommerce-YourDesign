package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Entity.Pokemon;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import com.youdesign.YouDesign.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NosotrosController {
    @Autowired
    private PokemonService pokemonService;
    private final CategoriaRepository categoriaRepository;

    public NosotrosController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/nosotros")
    public String mostrarNosostros(Model model) {
        model.addAttribute("pageTitle", "Sobre Nosotros");
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        Pokemon pokemon = pokemonService.getRandomPokemon();
        model.addAttribute("pokemon", pokemon);
        return "sobre_nosotros";
    }
}
