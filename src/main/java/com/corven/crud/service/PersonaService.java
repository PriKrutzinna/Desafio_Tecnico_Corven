package com.corven.crud.service;

import com.corven.crud.dto.EstadisticasDTO;
import com.corven.crud.model.Contacto;
import com.corven.crud.model.Persona;
import com.corven.crud.model.Relacion;
import com.corven.crud.repository.interfaces.IContactoRepository;
import com.corven.crud.repository.interfaces.IPersonaRepository;
import com.corven.crud.repository.interfaces.IRelacionRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private IPersonaRepository personaRepository;
    @Autowired
    private IContactoRepository contactoRepository;
    @Autowired
    private IRelacionRepository relacionRepository;

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Persona findById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    public EstadisticasDTO finEstadisticasDTO() {
        Map<String, Object> resultadoMap = personaRepository.obtenerCifrasTotalizadoras();
        EstadisticasDTO estadisticasDTO = new EstadisticasDTO();
        estadisticasDTO.setCantidadMujeres((Long) resultadoMap.get("cantidad_mujeres"));
        estadisticasDTO.setCantidadHombres((Long) resultadoMap.get("cantidad_hombres"));
        estadisticasDTO.setPorcentajeArgentinos((BigDecimal) resultadoMap.get("porcentaje_argentinos"));
        return estadisticasDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public Persona save(Persona persona) {
        Optional<Persona> existingPersona = personaRepository.findByTipoDocumentoAndDocumentoAndPaisAndGenero(
                persona.getTipoDocumento(),
                persona.getDocumento(),
                persona.getPais(),
                persona.getGenero());
        if (existingPersona.isPresent())
            return existingPersona.get();
        else {
            if (persona.getContactos() != null) {
                List<Contacto> savedContactos = contactoRepository.saveAll(persona.getContactos());
                System.out.println("CONTACTOS GUARDADOS EN DB: " + savedContactos);
            }
            return personaRepository.save(persona);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Persona persona) {
        List<Relacion> relacionesPersona=relacionRepository.findByPersonaPrincipalOrPersonaRelacionada(persona, persona);
        if(!relacionesPersona.isEmpty()){
            for (Relacion relacion : relacionesPersona) {
                relacionRepository.delete(relacion);
            }
        }
        personaRepository.delete(persona);
    }

    @Transactional(rollbackFor = Exception.class)
    public Persona updateContactosDePersona(Persona existingPersona, List<Contacto> nuevosContactos) {
        List<Contacto> contactosExistentes = existingPersona.getContactos();
        if (contactosExistentes != null) {
            for (Contacto contactoNuevo : nuevosContactos) {
                boolean tipoContactoYaExiste = false;
                for (Contacto existingContacto : contactosExistentes) {
                    if (contactoNuevo.getTipoContacto().getTipoContacto() == existingContacto.getTipoContacto()
                            .getTipoContacto()) {
                        tipoContactoYaExiste = true;
                        existingContacto.setDatoContacto(contactoNuevo.getDatoContacto());
                        contactoRepository.save(existingContacto);
                        System.out.println("CONTACTO ACTUALIZADO EN DB: " + existingContacto);
                    }
                }
                if (!tipoContactoYaExiste) {
                    contactoRepository.save(contactoNuevo);
                    System.out.println("CONTACTO CREADO EN DB: " + contactoNuevo);
                }
            }
        }
        return personaRepository.save(existingPersona);
    }
}
