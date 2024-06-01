package com.corven.crud.repository.interfaces;

import com.corven.crud.model.Persona;
import com.corven.crud.model.Relacion;
import com.corven.crud.model.TipoRelacion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRelacionRepository extends JpaRepository<Relacion, Long> {

    Optional<Relacion> findByTipoRelacionAndPersonaPrincipalAndPersonaRelacionada(TipoRelacion tipoRelacion, Persona personaPrincipal,
            Persona personaRelacionada);

    Optional<Relacion> findByPersonaPrincipalAndPersonaRelacionada(Persona persona1, Persona persona2);

    List<Relacion> findByPersonaPrincipalOrPersonaRelacionada(Persona persona, Persona persona2);
}
