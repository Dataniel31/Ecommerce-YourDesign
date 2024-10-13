package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Dto.UsuarioRegistrodto;
import com.youdesign.YouDesign.Entity.Usuario;

public interface UsuarioService {

    public Usuario save(UsuarioRegistrodto registrodto);
}
