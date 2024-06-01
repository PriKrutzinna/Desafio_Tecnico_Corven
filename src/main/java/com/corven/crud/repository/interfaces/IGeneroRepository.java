package com.corven.crud.repository.interfaces;

import com.corven.crud.model.Genero;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Long> {
    Optional<Genero> findByGeneroIgnoreCase(String genero);
}
