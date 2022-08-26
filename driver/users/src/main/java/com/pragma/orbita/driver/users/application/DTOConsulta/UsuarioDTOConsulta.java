package com.pragma.orbita.driver.users.application.DTOConsulta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTOConsulta {
    private int idUsuario;
    private String nombres;
    private String apellidos;
    private String documento;
    private String tipoDocumento;
    private String email;
    private String telefono;
}
