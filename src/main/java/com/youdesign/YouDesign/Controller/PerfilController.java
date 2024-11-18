package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Dto.UsuarioRegistrodto;
import com.youdesign.YouDesign.Entity.Pokemon;
import com.youdesign.YouDesign.Entity.Usuario;
import com.youdesign.YouDesign.Service.PokemonService;
import com.youdesign.YouDesign.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class PerfilController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model, Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioService.findByEmail(email);
        model.addAttribute("usuario", usuario);
        Pokemon pokemon = pokemonService.getRandomPokemon();
        model.addAttribute("pokemon", pokemon);
        return "perfil";
    }

    @PostMapping("/actualizarPerfil")
    public String actualizarPerfil(@ModelAttribute UsuarioRegistrodto usuarioDto,
                                   @RequestParam("currentPassword") String currentPassword,
                                   @RequestParam(value = "newPassword", required = false) String newPassword,
                                   Principal principal,
                                   RedirectAttributes redirectAttributes) {
        try {
            String email = principal.getName();
            Usuario usuario = usuarioService.findByEmail(email);

            // Verificar contraseña actual
            if (!passwordEncoder.matches(currentPassword, usuario.getPassword())) {
                redirectAttributes.addFlashAttribute("error", "La contraseña actual es incorrecta");
                return "redirect:/perfil";
            }

            // Actualizar datos básicos
            usuario.setNombre(usuarioDto.getNombre());
            usuario.setDireccion(usuarioDto.getDireccion());

            // Actualizar contraseña si se proporcionó una nueva
            if (newPassword != null && !newPassword.isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(newPassword));
            }

            usuarioService.updateRegistro(usuario.getId_usuario(), usuarioDto);
            redirectAttributes.addFlashAttribute("success", "Perfil actualizado correctamente");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el perfil");
        }
        return "redirect:/perfil";
    }
}