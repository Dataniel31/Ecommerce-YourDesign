package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Dto.UsuarioRegistrodto;
import com.youdesign.YouDesign.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistroController {
    private UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistrodto retornarnuevousuarioRegistrodto() {
        return new UsuarioRegistrodto();
    }

    @GetMapping("/registrar")
    public String MostrarRegistro() {
        return "registrar";
    }

    @PostMapping("/registrar")
    public String registrarCuentaDeUsuaro(@ModelAttribute("usuario") UsuarioRegistrodto registrodto) {
        usuarioService.save(registrodto);
        return "redirect:/registrar?exito";
    }
}
