package com.corven.crud.dto;

import java.math.BigDecimal;

public class EstadisticasDTO {
    private Long cantidadMujeres;
    private Long cantidadHombres;
    private BigDecimal porcentajeArgentinos;

    public Long getCantidadMujeres() {
        return cantidadMujeres;
    }

    public void setCantidadMujeres(Long cantidadMujeres) {
        this.cantidadMujeres = cantidadMujeres;
    }

    public Long getCantidadHombres() {
        return cantidadHombres;
    }

    public void setCantidadHombres(Long cantidadHombres) {
        this.cantidadHombres = cantidadHombres;
    }

    public BigDecimal getPorcentajeArgentinos() {
        return porcentajeArgentinos;
    }

    public void setPorcentajeArgentinos(BigDecimal porcentajeArgentinos) {
        this.porcentajeArgentinos = porcentajeArgentinos;
    }
}
