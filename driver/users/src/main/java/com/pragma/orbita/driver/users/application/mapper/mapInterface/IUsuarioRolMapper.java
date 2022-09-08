package com.pragma.orbita.driver.users.application.mapper.mapInterface;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioRolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDTORespuesta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioRolDTORespuesta;
import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUsuarioRolMapper {
    IUsuarioRolMapper INSTANCE = Mappers.getMapper(IUsuarioRolMapper.class);

    UsuarioRolDTORespuesta usuarioRolToDtoRespuesta (UsuarioRol usuarioRol);

    UsuarioRol respuestaDtoToUsuarioRol (UsuarioRolDTORespuesta usuarioRolDTORespuesta);

    UsuarioRolDTOConsulta usuarioRolToDtoConsulta (UsuarioRol usuarioRol);

    UsuarioRol consultaDtoToUsuarioRol (UsuarioRolDTOConsulta usuarioRolDTOConsulta);
}
