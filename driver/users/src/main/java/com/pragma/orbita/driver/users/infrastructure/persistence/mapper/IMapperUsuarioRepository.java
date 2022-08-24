package com.pragma.orbita.driver.users.infrastructure.persistence.mapper;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioEntity;
import org.mapstruct.factory.Mappers;

public interface IMapperUsuarioRepository {
    IMapperUsuarioRepository INSTANCE = Mappers.getMapper(IMapperUsuarioRepository.class);

    UsuarioEntity usuarioToEntity (Usuario usuario);

    Usuario entityToUsuario (UsuarioEntity usuarioEntity);
}
