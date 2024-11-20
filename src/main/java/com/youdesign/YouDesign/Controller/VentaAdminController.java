package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Compra;
import com.youdesign.YouDesign.Entity.Pokemon;
import com.youdesign.YouDesign.Entity.Usuario;
import com.youdesign.YouDesign.Repository.CompraRepository;
import com.youdesign.YouDesign.Repository.UsuarioRepository;
import com.youdesign.YouDesign.Service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/ventas")
public class VentaAdminController {
    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String MostrarVentas (Model model){
        model.addAttribute("pageTitle", "Lista de Ventas");
        List<Usuario> usuario = usuarioRepository.findAll();
        model.addAttribute("usuario", usuario);
        List<Compra> compra = compraRepository.findAll();
        model.addAttribute("compra", compra);
        Pokemon pokemon = pokemonService.getRandomPokemon();
        model.addAttribute("pokemon", pokemon);
        return "admin/ventas";
    }
}
