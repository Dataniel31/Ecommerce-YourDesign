package com.youdesign.YouDesign.Repository;

import com.youdesign.YouDesign.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
