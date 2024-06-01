package com.corven.crud.service;

import com.corven.crud.model.TipoDocumento;
import com.corven.crud.repository.interfaces.ITipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoDocumentoService {

    @Autowired
    private ITipoDocumentoRepository tipoDocumentoRepository;

    public Optional<TipoDocumento> findByTipoDocumento(String tipoDocumento) {
        return tipoDocumentoRepository.findByTipoDocumentoIgnoreCase(tipoDocumento);
    }
}
