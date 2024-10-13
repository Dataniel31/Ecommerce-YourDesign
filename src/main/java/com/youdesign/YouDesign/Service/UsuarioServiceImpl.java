package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Dto.UsuarioRegistrodto;
import com.youdesign.YouDesign.Entity.Rol;
import com.youdesign.YouDesign.Entity.Usuario;
import com.youdesign.YouDesign.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario save(UsuarioRegistrodto registrodto) {
        Usuario usuario = new Usuario(registrodto.getNombre(),
                registrodto.getDireccion(),registrodto.getEmail(),
                registrodto.getUsuario(),registrodto.getPassword(), Arrays.asList(new Rol("ROLE_USER")));
        return usuarioRepository.save(usuario);
    }
}
