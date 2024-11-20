package com.youdesign.YouDesign.Repository;

import com.youdesign.YouDesign.Entity.Compra;
import com.youdesign.YouDesign.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByUsuario(Usuario usuario);
}
