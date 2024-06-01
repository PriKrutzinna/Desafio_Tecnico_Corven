package com.corven.crud.service;

import com.corven.crud.model.Contacto;
import com.corven.crud.repository.interfaces.IContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ContactoService {

    @Autowired
    private IContactoRepository contactoRepository;

    public Contacto save(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    public Optional<Contacto> findByNumero(String numero) {
        return contactoRepository.findByDatoContacto(numero);
    }

}
