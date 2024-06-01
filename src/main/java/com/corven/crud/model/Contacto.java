package com.corven.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String datoContacto;

    @ManyToOne
    @JoinColumn(name = "tipo_contacto_id", nullable = false)
    private TipoContacto tipoContacto;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    @JsonIgnore
    private Persona persona;

    public Contacto() {
    }

    public Contacto(TipoContacto tipoContacto, String datoContacto, Persona persona) {
        this.datoContacto = datoContacto;
        this.tipoContacto = tipoContacto;
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatoContacto() {
        return datoContacto;
    }

    public void setDatoContacto(String valor) {
        this.datoContacto = valor;
    }

    public TipoContacto getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(TipoContacto tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String toString() {
        return this.tipoContacto + " | CONTACTO: "+this.datoContacto+" (ID: " + this.id + ")";
    }
}
