package com.corven.crud.repository.interfaces;

import com.corven.crud.model.Pais;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaisRepository extends JpaRepository<Pais, Long> {
    Optional<Pais> findByPaisIgnoreCase(String pais);
}
