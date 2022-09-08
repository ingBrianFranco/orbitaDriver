package com.pragma.orbita.driver.users.application.respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjetoRespuesta<T> {
    private T dato;
    private String message;
}
