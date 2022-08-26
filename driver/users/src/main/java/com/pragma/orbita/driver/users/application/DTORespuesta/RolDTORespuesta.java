package com.pragma.orbita.driver.users.application.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDTORespuesta {
    private int idRol;
    private String nombre;
    private String descripcion;
}
