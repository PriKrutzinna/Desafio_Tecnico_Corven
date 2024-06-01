package com.corven.crud.dto;

public class ContactoDTO {
    private String tipo;
    private String datoContacto;

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setDatoContacto(String contacto) {
        this.datoContacto = contacto;
    }

    public String getDatoContacto() {
        return datoContacto;
    }

    public String toString() {
        return this.getTipo() + " " + this.getDatoContacto();
    }
}
