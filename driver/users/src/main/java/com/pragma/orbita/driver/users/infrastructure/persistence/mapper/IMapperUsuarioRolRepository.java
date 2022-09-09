package com.pragma.orbita.driver.users.infrastructure.persistence.mapper;

import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioRolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface IMapperUsuarioRolRepository {
    IMapperUsuarioRolRepository INSTANCE = Mappers.getMapper(IMapperUsuarioRolRepository.class);

    UsuarioRolEntity usuarioRolToEntity (UsuarioRol usuarioRol);

    UsuarioRol entityToUsuarioRol (UsuarioRolEntity usuarioRolEntity);
}
