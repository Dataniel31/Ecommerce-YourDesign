package com.youdesign.YouDesign.Controller;

import com.youdesign.YouDesign.Dto.Compradto;
import com.youdesign.YouDesign.Entity.Compra;
import com.youdesign.YouDesign.Entity.Usuario;
import com.youdesign.YouDesign.Service.CompraService;
import com.youdesign.YouDesign.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/compras")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuarioService userService;

    @PostMapping("/realizar")
    public ResponseEntity<?> realizarCompra(@RequestBody Compradto compraDTO,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Usuario usuario = userService.findByEmail(userDetails.getUsername());
            Compra compra = compraService.realizarCompra(compraDTO, usuario);
            compraService.generarPDF(compra);
            return ResponseEntity.ok(compra);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}

