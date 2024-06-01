package com.corven.crud.controller;

import com.corven.crud.model.Persona;
import com.corven.crud.model.Relacion;
import com.corven.crud.service.PersonaService;
import com.corven.crud.service.RelacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relaciones")
public class RelacionController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private RelacionService relacionService;


    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<?> obtenerRelacion(@PathVariable Long id1, @PathVariable Long id2) {
        try {
            Persona personaPrincipal = personaService.findById(id1);
            Persona personaRelacionada = personaService.findById(id2);
            if (personaPrincipal == null || personaRelacionada == null)
                throw new IllegalArgumentException("No se encontró una de las personas especificadas.");
            
            Relacion relacion = relacionService.obtenerRelacion(personaPrincipal, personaRelacionada);
            return ResponseEntity.ok(relacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
