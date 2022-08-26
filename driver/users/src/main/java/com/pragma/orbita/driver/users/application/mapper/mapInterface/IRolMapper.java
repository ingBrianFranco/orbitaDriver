package com.pragma.orbita.driver.users.application.mapper.mapInterface;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDTORespuesta;
import com.pragma.orbita.driver.users.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface IRolMapper {
    IRolMapper INSTANCE = Mappers.getMapper(IRolMapper.class);

    RolDTORespuesta rolToDtoRespuesta (Rol rol);

    Rol respuestaDtoToRol (RolDTORespuesta rolDTORespuesta);

    RolDTOConsulta rolToDtoConsulta (Rol rol);

    Rol consultaDtoToRol (RolDTOConsulta rolDTOConsulta);
}
