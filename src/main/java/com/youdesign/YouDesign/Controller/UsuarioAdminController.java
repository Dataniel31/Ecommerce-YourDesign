package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Dto.UsuarioRegistrodto;
import com.youdesign.YouDesign.Entity.Rol;
import com.youdesign.YouDesign.Entity.Usuario;
import com.youdesign.YouDesign.Repository.RolRepository;
import com.youdesign.YouDesign.Repository.UsuarioRepository;
import com.youdesign.YouDesign.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/usuarios")
public class UsuarioAdminController {
    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepository;

    public UsuarioAdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String usuario(Model model) {
        model.addAttribute("usuario", new UsuarioRegistrodto());
        model.addAttribute("pageTitle", "Registro Usuarios");
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("roles", rolRepository.findAll());
        return "admin/usuarios";
    }
    @PostMapping
    public String guardarUsuario(@ModelAttribute("usuario") UsuarioRegistrodto registrodto){
        Rol rolUsuario = rolRepository.findByNombre(registrodto.getRol());
        if (rolUsuario == null) {
            rolUsuario = new Rol(registrodto.getRol());
            rolRepository.save(rolUsuario);
        }
        usuarioService.saveUsuario(registrodto, rolUsuario);
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, @ModelAttribute("usuario") UsuarioRegistrodto registrodto) {
        Rol rolUsuario = rolRepository.findByNombre(registrodto.getRol());
        if (rolUsuario == null) {
            rolUsuario = new Rol(registrodto.getRol());
            rolRepository.save(rolUsuario);
        }
        usuarioService.updateUsuario(id, registrodto, rolUsuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return "redirect:/admin/usuarios";
    }
}
