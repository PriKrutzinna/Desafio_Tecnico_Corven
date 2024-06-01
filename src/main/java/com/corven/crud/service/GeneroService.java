package com.corven.crud.service;

import com.corven.crud.model.Genero;
import com.corven.crud.repository.interfaces.IGeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    private IGeneroRepository generoRepository;

    public Optional<Genero> findByNombre(String nombre) {
        return generoRepository.findByGeneroIgnoreCase(nombre);
    }
}
