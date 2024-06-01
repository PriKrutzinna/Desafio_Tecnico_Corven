package com.corven.crud.service;

import com.corven.crud.model.TipoContacto;
import com.corven.crud.repository.interfaces.ITipoContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoContactoService {

    @Autowired
    private ITipoContactoRepository tipoContactoRepository;

    public Optional<TipoContacto> findByTipoContacto(String tipoContacto) {
        return tipoContactoRepository.findByTipoContactoIgnoreCase(tipoContacto);
    }
}
