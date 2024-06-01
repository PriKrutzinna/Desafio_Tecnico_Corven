package com.corven.crud.service;

import com.corven.crud.model.Persona;
import com.corven.crud.model.Relacion;
import com.corven.crud.model.TipoRelacion;
import com.corven.crud.repository.interfaces.IRelacionRepository;
import com.corven.crud.repository.interfaces.ITipoRelacionRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelacionService {

    @Autowired
    private ITipoRelacionRepository tipoRelacionRepository;
    @Autowired
    private IRelacionRepository relacionRepository;

    public Optional<TipoRelacion> findByTipoRelacion(String tipo) {
        return tipoRelacionRepository.findByTipoRelacionIgnoreCase(tipo);
    }

    public Relacion establecerRelacion(Persona persona1, Persona persona2, TipoRelacion tipoRelacion) {
        Relacion relacion=new Relacion();
        relacion.setPersonaPrincipal(persona1);
        relacion.setTipoRelacion(tipoRelacion);
        relacion.setPersonaRelacionada(persona2);
        return relacionRepository.save(relacion);
    }

    public Relacion obtenerRelacion(Persona persona1, Persona persona2, TipoRelacion tipoRelacion) {
        return relacionRepository.findByTipoRelacionAndPersonaPrincipalAndPersonaRelacionada(tipoRelacion, persona1, persona2).orElse(null);
    }

    public Relacion obtenerRelacion(Persona persona1, Persona persona2) {
        return relacionRepository.findByPersonaPrincipalAndPersonaRelacionada(persona1, persona2).orElse(null);
    }

    public Relacion findByTipoRelacionAndpersonaPrincipalAndPersonaRelacionada(TipoRelacion tipoRelacion,
            Persona personaPrincipal, Persona personaRelacionada) {
        return relacionRepository.findByTipoRelacionAndPersonaPrincipalAndPersonaRelacionada(tipoRelacion, personaPrincipal, personaRelacionada).orElse(null);
    }

    public List<Relacion> obtenerRelacionesDePersona(Persona persona) {
        return relacionRepository.findByPersonaPrincipalOrPersonaRelacionada(persona, persona);
    }
}
