package com.pragma.orbita.driver.users.domain.respuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjetoRespuestaDomain<T> {
    private T dato;
    private String message;
}
