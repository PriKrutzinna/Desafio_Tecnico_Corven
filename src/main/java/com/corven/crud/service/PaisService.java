package com.corven.crud.service;

import com.corven.crud.model.Pais;
import com.corven.crud.repository.interfaces.IPaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaisService {

    @Autowired
    private IPaisRepository paisRepository;

    public Optional<Pais> findByNombre(String nombre) {
        return paisRepository.findByPaisIgnoreCase(nombre);
    }
}
