package com.corven.crud.controller;

import com.corven.crud.dto.ContactoDTO;
import com.corven.crud.dto.PersonaDTO;
import com.corven.crud.model.Contacto;
import com.corven.crud.model.Genero;
import com.corven.crud.model.Pais;
import com.corven.crud.model.Persona;
import com.corven.crud.model.Relacion;
import com.corven.crud.model.TipoDocumento;
import com.corven.crud.model.TipoRelacion;
import com.corven.crud.model.TipoContacto;
import com.corven.crud.service.GeneroService;
import com.corven.crud.service.PaisService;
import com.corven.crud.service.PersonaService;
import com.corven.crud.service.RelacionService;
import com.corven.crud.service.TipoContactoService;
import com.corven.crud.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @Autowired
    private TipoContactoService tipoContactoService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private RelacionService relacionService;

    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.findAll();
    }

    @GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable Long id) {
        return personaService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createPersona(@RequestBody PersonaDTO personaDTO) {
        try {
            TipoDocumento tipoDocumento = tipoDocumentoService.findByTipoDocumento(personaDTO.getTipoDocumento())
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de documento no válido."));
            Pais pais = paisService.findByNombre(personaDTO.getPais())
                    .orElseThrow(() -> new IllegalArgumentException("País no válido."));
            Genero genero = generoService.findByNombre(personaDTO.getGenero())
                    .orElseThrow(() -> new IllegalArgumentException("Género no válido."));

            Persona persona = new Persona(tipoDocumento, personaDTO.getNumeroDocumento(), pais, genero,
                    validarFechaNacimiento(personaDTO.getFechaNacimiento()));
            persona.setNombre(personaDTO.getNombre());
            persona.setApellido(personaDTO.getApellido());

            Persona savedPersona = personaService.save(persona);
            List<Contacto> contactosDeLaPersona = validarContactos(personaDTO.getContactos(), savedPersona);
            savedPersona.setContactos(contactosDeLaPersona);
            return ResponseEntity.ok(personaService.updateContactosDePersona(savedPersona, contactosDeLaPersona));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private List<Contacto> validarContactos(List<ContactoDTO> contactosDTO, Persona persona) {
        List<Contacto> contactos = new ArrayList<Contacto>();
        System.out.println("CONTACTOS A CREAR: " + contactosDTO);
        contactosDTO.forEach(contactoDTO -> {
            TipoContacto tipoContacto = tipoContactoService.findByTipoContacto(contactoDTO.getTipo())
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de contacto no válido."));
            contactos.forEach(contacto -> {
                if (contacto.getTipoContacto().getTipoContacto() == tipoContacto.getTipoContacto()) {
                    throw new IllegalArgumentException(
                            "No se puede tener más de un tipo de contacto '" + tipoContacto.getTipoContacto() + "'.");
                }
            });
            Contacto contacto = new Contacto(tipoContacto, contactoDTO.getDatoContacto(), persona);
            contactos.add(contacto);
        });
        return contactos;
    }

    private Date validarFechaNacimiento(String fechaNacimientoStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);

            Calendar fechaNacimientoCalendar = Calendar.getInstance();
            fechaNacimientoCalendar.setTime(fechaNacimiento);
            Calendar fechaActualCalendar = Calendar.getInstance();

            int edadAños = fechaActualCalendar.get(Calendar.YEAR) - fechaNacimientoCalendar.get(Calendar.YEAR);

            if (fechaActualCalendar.get(Calendar.MONTH) < fechaNacimientoCalendar.get(Calendar.MONTH) ||
                    (fechaActualCalendar.get(Calendar.MONTH) == fechaNacimientoCalendar.get(Calendar.MONTH) &&
                            fechaActualCalendar.get(Calendar.DAY_OF_MONTH) < fechaNacimientoCalendar
                                    .get(Calendar.DAY_OF_MONTH)))
                edadAños--;
            if (edadAños < 18)
                throw new IllegalArgumentException("La persona debe ser mayor de 18 años.");
            return fechaNacimiento;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de fecha de nacimiento inválido: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        // Solo actualizo Contactos ya que los demas atributos son identificadores clave
        try {
            Persona existingPersona = personaService.findById(id);
            if (existingPersona == null)
                throw new IllegalArgumentException("No existe una persona con id: " + id);
            List<Contacto> nuevosContactos = validarContactos(personaDTO.getContactos(), existingPersona);
            Persona savedPersona = personaService.updateContactosDePersona(existingPersona, nuevosContactos);
            return ResponseEntity.ok(savedPersona);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable Long id) {
        try {
            Persona existingPersona = personaService.findById(id);
            if (existingPersona == null)
                throw new IllegalArgumentException("No existe una persona con id: " + id);
            personaService.delete(existingPersona);
            return ResponseEntity.ok("Persona ID " + id + " Eliminada junto con sus Relaciones.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{personaPrincipalID}/{tipoRelacion}/{personaRelacionadaID}")
    public ResponseEntity<?> crearRelacion(@PathVariable Long personaPrincipalID, @PathVariable String tipoRelacion,
            @PathVariable Long personaRelacionadaID) {
        try {
            TipoRelacion tipo = relacionService.findByTipoRelacion(tipoRelacion)
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de relación no válida."));
            Persona personaPrincipal = personaService.findById(personaPrincipalID);
            Persona personaRelacionada = personaService.findById(personaRelacionadaID);

            if (personaPrincipalID == null || personaRelacionada == null)
                throw new IllegalArgumentException("No se encontró una de las personas especificadas.");
            Relacion relacionExistente = relacionService.findByTipoRelacionAndpersonaPrincipalAndPersonaRelacionada(
                tipo, personaPrincipal, personaRelacionada);
            if (relacionExistente != null)
                throw new IllegalArgumentException("Ya existe ese tipo de relacion entre las personas especificadas.");
            Relacion savedRelacion=relacionService.establecerRelacion(personaPrincipal, personaRelacionada, tipo);
            return ResponseEntity.ok(savedRelacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
