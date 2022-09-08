package com.pragma.orbita.driver.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRol {
    private int idUsuarioRol;
    private int idUsuario;
    private int idRol;
}
