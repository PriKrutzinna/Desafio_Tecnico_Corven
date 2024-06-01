package com.corven.crud.repository.interfaces;

import com.corven.crud.model.TipoRelacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoRelacionRepository extends JpaRepository<TipoRelacion, Long> {
    Optional<TipoRelacion> findByTipoRelacionIgnoreCase(String tipoRelacion);
}
