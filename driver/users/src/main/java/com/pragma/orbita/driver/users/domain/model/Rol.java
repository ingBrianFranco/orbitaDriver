package com.pragma.orbita.driver.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    private int idRol;
    @NotNull(message = "el campo nombre es requerido")
    @NotEmpty(message = "el campo nombre es requerido")
    @NotBlank(message = "el campo nombre no pueden ser espacios en blanco")
    private String nombre;
    private String descripcion;
}
