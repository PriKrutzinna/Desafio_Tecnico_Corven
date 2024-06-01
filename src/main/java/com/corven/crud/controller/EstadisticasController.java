package com.corven.crud.controller;

import com.corven.crud.dto.EstadisticasDTO;
import com.corven.crud.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public EstadisticasDTO getEstadisticasDTO() {
        return personaService.finEstadisticasDTO();
    }
}
