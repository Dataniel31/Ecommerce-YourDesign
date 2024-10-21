package com.youdesign.YouDesign.Repository;

import com.youdesign.YouDesign.Entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Marca findByNombre(String nombre);
}
