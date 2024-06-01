package com.corven.crud.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corven.crud.model.TipoContacto;

import java.util.Optional;


@Repository
public interface ITipoContactoRepository extends JpaRepository<TipoContacto, Long> {
    Optional<TipoContacto> findByTipoContactoIgnoreCase(String tipoContacto);
}
