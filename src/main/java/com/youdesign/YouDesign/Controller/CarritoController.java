package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Entity.Pokemon;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import com.youdesign.YouDesign.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired
    private PokemonService pokemonService;
    private final CategoriaRepository categoriaRepository;

    public CarritoController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    @GetMapping
    public String mostrarcarrito(Model model){
        model.addAttribute("pageTitle", "Carrito de compras");
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        Pokemon pokemon = pokemonService.getRandomPokemon();
        model.addAttribute("pokemon", pokemon);
        return "carrito";
    }
}
