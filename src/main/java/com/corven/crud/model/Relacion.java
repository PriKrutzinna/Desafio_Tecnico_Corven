package com.corven.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Relacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_principal_id", nullable = false)
    private Persona personaPrincipal;

    @ManyToOne
    @JoinColumn(name = "persona_relacionada_id", nullable = false)
    private Persona personaRelacionada;

    @ManyToOne
    @JoinColumn(name = "tipo_relacion_id", nullable = false)
    private TipoRelacion tipoRelacion;

    public Relacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersonaPrincipal() {
        return personaPrincipal;
    }

    public void setPersonaPrincipal(Persona personaPrincipal) {
        this.personaPrincipal = personaPrincipal;
    }

    public Persona getPersonaRelacionada() {
        return personaRelacionada;
    }

    public void setPersonaRelacionada(Persona personaRelacionada) {
        this.personaRelacionada = personaRelacionada;
    }

    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }
}
