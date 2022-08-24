package com.pragma.orbita.driver.users.infrastructure.respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ObjetoRespuestaInfrastructure<T> {
    private HttpStatus status;
    private T dato;
    private String message;
}
