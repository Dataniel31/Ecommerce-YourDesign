package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Pokemon;
import com.youdesign.YouDesign.Entity.Reclamacion;
import com.youdesign.YouDesign.Repository.ReclamacionRepository;
import com.youdesign.YouDesign.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/lista_reclamaciones")
public class ReclamacionAdminController {
    @Autowired
    private ReclamacionRepository reclamacionRepository;
    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public String listarReclamaciones(Model model){
        model.addAttribute("pageTitle", "Lista de Reclamaciones");
        List<Reclamacion> reclamaciones = reclamacionRepository.findAll();
        model.addAttribute("reclamaciones", reclamaciones);
        Pokemon pokemon = pokemonService.getRandomPokemon();
        model.addAttribute("pokemon", pokemon);
        return "/admin/lista_reclamaciones";
    }
}
