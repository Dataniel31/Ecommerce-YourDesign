package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Pokemon;
import com.youdesign.YouDesign.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/home")
public class HomeAdminController {
    @Autowired
    private PokemonService pokemonService;
    @GetMapping
    public String mostrarhomeAdmin (Model model){
        model.addAttribute("pageTitle", "Home Admin");
        Pokemon pokemon = pokemonService.getRandomPokemon();
        model.addAttribute("pokemon", pokemon);
        return "admin/adminhome";
    }
}
