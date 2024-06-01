package com.corven.crud.model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Contacto> contactos;

    public Persona() {
    }

    public Persona(TipoDocumento tipoDocumento, String documento, Pais pais, Genero genero, Date fechaNacimiento,
            List<Contacto> contactos) {
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.pais = pais;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.contactos = contactos;
    }

    public Persona(TipoDocumento tipoDocumento, String documento, Pais pais, Genero genero, Date fechaNacimiento) {
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.pais = pais;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public void addContacto(Contacto contacto) {
        this.contactos.add(contacto);
    }

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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String toString() {
        return "PERSONA (ID " + this.id + "): Documento=" + this.getDocumento() + " | Nombre="
                + this.getNombre()
                + " | Apellido=" + this.getApellido() + " | Pais=" + this.getPais() + " | Genero=" + this.getGenero()
                + " | Contacto=" + this.getContactos();
    }
}
