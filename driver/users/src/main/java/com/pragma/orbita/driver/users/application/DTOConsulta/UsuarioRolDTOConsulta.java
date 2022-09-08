package com.pragma.orbita.driver.users.application.DTOConsulta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRolDTOConsulta {
    private int idUsuarioRol;
    private int idUsuario;
    private int idRol;
}
