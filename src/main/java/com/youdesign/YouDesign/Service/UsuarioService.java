package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Dto.UsuarioRegistrodto;
import com.youdesign.YouDesign.Entity.Rol;
import com.youdesign.YouDesign.Entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UsuarioService extends UserDetailsService {
    Usuario save(UsuarioRegistrodto registrodto);
    Usuario saveUsuario(UsuarioRegistrodto registrodto, Rol rol);
    Usuario updateUsuario(Long id ,UsuarioRegistrodto registrodto, Rol rol);
    Usuario updateRegistro(Long id ,UsuarioRegistrodto registrodto);
    void deleteUsuario(Long id_usuario);
    Usuario findById(Long id_usuario);
    List<Usuario> findAll();
    Usuario findByEmail(String email);
}
