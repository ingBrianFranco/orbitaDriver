package com.pragma.orbita.driver.users.domain.repository;

import com.pragma.orbita.driver.users.domain.model.Usuario;

import java.util.Optional;
import java.util.stream.Stream;

public interface IUsuarioRepository {
    
    Optional<Usuario> getUsuarioById(int idUsuario);

    Usuario guardarUsuario(Usuario Usuario);

    Boolean existeUsuarioById(int idUsuario);

    void eliminarUsuarioById(int idUsuario);

    Stream<Usuario> obtenerTodosUsuarios();
}
