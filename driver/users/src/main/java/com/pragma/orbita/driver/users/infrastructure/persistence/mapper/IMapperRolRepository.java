package com.pragma.orbita.driver.users.infrastructure.persistence.mapper;

import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.RolEntity;
import org.mapstruct.factory.Mappers;

public interface IMapperRolRepository {

    IMapperRolRepository INSTANCE = Mappers.getMapper(IMapperRolRepository.class);

    RolEntity rolToEntity (Rol rol);

    Rol entityToRol(RolEntity rolEntity);
}
