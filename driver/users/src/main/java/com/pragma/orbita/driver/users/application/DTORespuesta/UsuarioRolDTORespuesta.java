package com.pragma.orbita.driver.users.application.DTORespuesta;

import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRolDTORespuesta {
    private int idUsuarioRol;
    private Usuario usuario;
    private Rol rol;
}
