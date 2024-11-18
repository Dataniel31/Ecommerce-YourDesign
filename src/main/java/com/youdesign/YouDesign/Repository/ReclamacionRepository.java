package com.youdesign.YouDesign.Repository;

import com.youdesign.YouDesign.Entity.Reclamacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamacionRepository extends JpaRepository<Reclamacion, Long> {
}
