package org.jala.foundation.dev32.jwt.example.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductDto {

    @NotBlank
    private String nombre;
    @Min(0)
    private Float precio;

    public ProductDto() {
    }

    public ProductDto(@NotBlank String nombre, @Min(0) Float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}