package com.pragma.orbita.driver.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String documento;
    private String tipoDocumento;
    private String email;
    private String telefono;
}
