package com.pragma.orbita.driver.users.application.mapper.mapInterface;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDTORespuesta;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUsuarioMapper {
    
    IUsuarioMapper INSTANCE = Mappers.getMapper(IUsuarioMapper.class);

    UsuarioDTORespuesta usuarioToDtoRespuesta(Usuario Usuario);

    Usuario respuestaDtoToUsuario(UsuarioDTORespuesta UsuarioDTORespuesta);

    UsuarioDTOConsulta usuarioToDtoConsulta (Usuario Usuario);

    Usuario consultaDtoToUsuario (UsuarioDTOConsulta UsuarioDTOConsulta);
}
