package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Entity.Categoria;
import com.youdesign.YouDesign.Entity.Pokemon;
import com.youdesign.YouDesign.Entity.Reclamacion;
import com.youdesign.YouDesign.Entity.Usuario;
import com.youdesign.YouDesign.Repository.CategoriaRepository;
import com.youdesign.YouDesign.Repository.ReclamacionRepository;
import com.youdesign.YouDesign.Service.PokemonService;
import com.youdesign.YouDesign.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("lreclamaciones")
public class
ReclamacionesController {
    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ReclamacionRepository reclamacionRepository;
    private final CategoriaRepository categoriaRepository;

    public ReclamacionesController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public String MostrarReclamaciones(Model model, Principal principal) {
        model.addAttribute("pageTitle", "Libro de Reclamaciones");
        List<Categoria> categoria = categoriaRepository.findAll();
        model.addAttribute("categoria", categoria);
        String email = principal.getName();
        Usuario usuario = usuarioService.findByEmail(email);
        model.addAttribute("usuario", usuario);
        Pokemon pokemon = pokemonService.getRandomPokemon();
        model.addAttribute("pokemon", pokemon);
        return "libro_reclamaciones";
    }

    @PostMapping
    public String guardarReclamacion(@RequestParam("nombreCompleto") String nombreCompleto,
                                     @RequestParam("correoElectronico") String correoElectronico,
                                     @RequestParam("asunto") String asunto,
                                     @RequestParam("mensaje") String mensaje,
                                     Model model, Principal principal){
        Reclamacion reclamacion = new Reclamacion();
        reclamacion.setNombreCompleto(nombreCompleto);
        reclamacion.setCorreoElectronico(correoElectronico);
        reclamacion.setAsunto(asunto);
        reclamacion.setMensaje(mensaje);
        reclamacionRepository.save(reclamacion);
        model.addAttribute("pageTitle", "Libro de Reclamaciones");
        Pokemon pokemon = pokemonService.getRandomPokemon();
        String email = principal.getName();
        Usuario usuario = usuarioService.findByEmail(email);
        model.addAttribute("usuario", usuario);
        model.addAttribute("pokemon", pokemon);
        model.addAttribute("mensajeConfirmacion", "Su reclamo ha sido registrado correctamente");
        return "/libro_reclamaciones";
    }
}
