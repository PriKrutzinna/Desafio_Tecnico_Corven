package com.corven.crud.repository.interfaces;

import com.corven.crud.model.Contacto;
import com.corven.crud.model.TipoContacto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IContactoRepository extends JpaRepository<Contacto, Long> {
    Optional<Contacto> findByDatoContacto(String dataContacto);
    List<Contacto> findByTipoContactoAndDatoContacto(TipoContacto tipoContacto, String dataContacto);
}
