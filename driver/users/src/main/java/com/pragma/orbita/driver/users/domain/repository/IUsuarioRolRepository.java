package com.pragma.orbita.driver.users.domain.repository;

import com.pragma.orbita.driver.users.domain.model.UsuarioRol;

import java.util.Optional;
import java.util.stream.Stream;

public interface IUsuarioRolRepository {

    Optional<UsuarioRol> getUsuarioRolById(int idUsuarioRol);

    UsuarioRol guardarUsuarioRol(UsuarioRol usuarioRol);

    Boolean existeUsuarioById(int idUsuarioRol);

    void eliminarUsuarioById(int idUsuarioRol);

    Stream<UsuarioRol> obtenerTodosUsuariosRol();
}
