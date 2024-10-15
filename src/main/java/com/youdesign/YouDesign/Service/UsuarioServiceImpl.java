package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Dto.UsuarioRegistrodto;
import com.youdesign.YouDesign.Entity.Rol;
import com.youdesign.YouDesign.Entity.Usuario;
import com.youdesign.YouDesign.Repository.RolRepository;
import com.youdesign.YouDesign.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario save(UsuarioRegistrodto registrodto) {
        Rol rolUsuario = rolRepository.findByNombre("ROLE_USER");
        if (rolUsuario == null) {
            rolUsuario = new Rol("ROLE_USER");
            rolRepository.save(rolUsuario);
        }
        Usuario usuario = new Usuario(
                registrodto.getNombre(),
                registrodto.getDireccion(),
                registrodto.getEmail(),
                passwordEncoder.encode(registrodto.getPassword()),
                rolUsuario
        );
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getEmail(), usuario.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(usuario.getRol().getNombre())));
    }
}
