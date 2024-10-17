package com.youdesign.YouDesign.Repository;

import com.youdesign.YouDesign.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Productos, Long> {
}
