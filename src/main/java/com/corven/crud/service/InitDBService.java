package com.corven.crud.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import com.corven.crud.model.Genero;
import com.corven.crud.model.Pais;
import com.corven.crud.model.TipoContacto;
import com.corven.crud.model.TipoDocumento;
import com.corven.crud.model.TipoRelacion;
import com.corven.crud.repository.interfaces.IGeneroRepository;
import com.corven.crud.repository.interfaces.IPaisRepository;
import com.corven.crud.repository.interfaces.ITipoContactoRepository;
import com.corven.crud.repository.interfaces.ITipoDocumentoRepository;
import com.corven.crud.repository.interfaces.ITipoRelacionRepository;

import jakarta.annotation.PostConstruct;

@Service
public class InitDBService {

    private final ITipoDocumentoRepository tipoDocumentoRepository;
    private final IPaisRepository paisRepository;
    private final IGeneroRepository generoRepository;
    private final ITipoContactoRepository tipoContactoRepository;
    private final ITipoRelacionRepository tipoRelacionRepository;

    public InitDBService(ITipoDocumentoRepository tipoDocumentoRepository, IPaisRepository paisRepository,
            IGeneroRepository generoRepository, ITipoContactoRepository tipoContactoRepository, ITipoRelacionRepository tipoRelacionRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.paisRepository = paisRepository;
        this.generoRepository = generoRepository;
        this.tipoContactoRepository = tipoContactoRepository;
        this.tipoRelacionRepository = tipoRelacionRepository;

    }

    @PostConstruct
    public void init() {
        initTipoDocumento();
        initPais();
        initGenero();
        initTipoContacto();
        initTipoRelacion();
    }

    private void initTipoDocumento() {
        List<String> tiposDocumento = Arrays.asList("DNI", "LE", "CUIL", "CUIT", "PASS");
        for (String tipo : tiposDocumento) {
            TipoDocumento tipoDocumento = tipoDocumentoRepository.findByTipoDocumentoIgnoreCase(tipo).orElse(null);
            if (tipoDocumento == null) {
                tipoDocumento = new TipoDocumento();
                tipoDocumento.setTipoDocumento(tipo);
                tipoDocumentoRepository.save(tipoDocumento);
            }
        }
    }

    private void initPais() {
        List<String> paisesAmericaSur = Arrays.asList("Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Ecuador",
                "Guyana", "Paraguay", "Perú", "Surinam", "Uruguay", "Venezuela");
        for (String pais : paisesAmericaSur) {
            Pais paisObj = paisRepository.findByPaisIgnoreCase(pais).orElse(null);
            if (paisObj == null) {
                paisObj = new Pais();
                paisObj.setPais(pais);
                paisRepository.save(paisObj);
            }
        }
    }

    private void initGenero() {
        List<String> generos = Arrays.asList("Masculino", "Femenino", "No Binario");
        for (String genero : generos) {
            Genero generoObj = generoRepository.findByGeneroIgnoreCase(genero).orElse(null);
            if (generoObj == null) {
                generoObj = new Genero();
                generoObj.setGenero(genero);
                generoRepository.save(generoObj);
            }
        }
    }

    private void initTipoContacto() {
        List<String> tiposContacto = Arrays.asList("Correo", "Telefono", "Whatsapp");
        for (String tipo : tiposContacto) {
            TipoContacto tipoContacto = tipoContactoRepository.findByTipoContactoIgnoreCase(tipo).orElse(null);
            if (tipoContacto == null) {
                tipoContacto = new TipoContacto();
                tipoContacto.setTipoContacto(tipo);
                tipoContactoRepository.save(tipoContacto);
            }
        }
    }

    private void initTipoRelacion() {
        List<String> tiposRelacion = Arrays.asList("Hermano", "Primo", "Tio");
        for (String tipo : tiposRelacion) {
            TipoRelacion tipoRelacion = tipoRelacionRepository.findByTipoRelacionIgnoreCase(tipo).orElse(null);
            if (tipoRelacion == null) {
                tipoRelacion = new TipoRelacion();
                tipoRelacion.setTipoRelacion(tipo);
                tipoRelacionRepository.save(tipoRelacion);
            }
        }
    }
}
