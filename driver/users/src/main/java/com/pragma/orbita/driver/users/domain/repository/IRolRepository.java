package com.pragma.orbita.driver.users.domain.repository;

import com.pragma.orbita.driver.users.domain.model.Rol;

import java.util.Optional;
import java.util.stream.Stream;

public interface IRolRepository {

    Optional<Rol> getRolById(int idRol);

    Rol guardarRol(Rol Rol);

    Boolean existeRolById(int idRol);

    void eliminarRolById(int idRol);

    Stream<Rol> obtenerTodosRol();
}
