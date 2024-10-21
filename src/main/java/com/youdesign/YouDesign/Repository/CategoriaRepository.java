package com.youdesign.YouDesign.Repository;

import com.youdesign.YouDesign.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Categoria findByNombre(String nombre);
}
