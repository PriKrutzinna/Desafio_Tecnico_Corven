package com.corven.crud.dto;

import java.util.List;

public class PersonaDTO {
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String tipoDocumento;
    private String pais;
    private String genero;
    private String fechaNacimiento;
    private List<ContactoDTO> contactos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<ContactoDTO> getContactos() {
        return contactos;
    }

    public void setContactos(List<ContactoDTO> contactos) {
        this.contactos = contactos;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Apellido: " + apellido + " | NumeroDocumento: " + numeroDocumento
                + " | TipoDocumento: " + tipoDocumento + " | Pais: " + pais + " | Genero: " + genero + " | Fecha Nac: "
                + fechaNacimiento + " | Contactos: " + contactos;
    }
}
