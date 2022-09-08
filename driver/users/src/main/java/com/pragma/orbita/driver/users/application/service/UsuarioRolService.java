package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioRolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioRolDTORespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IUsuarioRolMapper;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.usecase.UsuarioRolUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioRolService {
    
    private final UsuarioRolUseCase usuarioRolUseCase;

    public ObjetoRespuesta<UsuarioRolDTORespuesta> buscarUsuarioRolPorId(int idRol){
        UsuarioRol respuesta = usuarioRolUseCase.getUsuarioRolById(idRol);

        if(respuesta == null){
            return new ObjetoRespuesta<UsuarioRolDTORespuesta>(null, "El usuarioRol no existe en el sistema");
        }

        UsuarioRolDTORespuesta usuarioRolDTORespuesta = IUsuarioRolMapper.INSTANCE.usuarioRolToDtoRespuesta(respuesta);

        return new ObjetoRespuesta<UsuarioRolDTORespuesta>(usuarioRolDTORespuesta, "Rol encontrado");
    }

    public ObjetoRespuesta<UsuarioRolDTORespuesta> guardarUsarioRol(UsuarioRolDTOConsulta usuarioRolDTOConsulta){
        UsuarioRol usuarioRol = IUsuarioRolMapper.INSTANCE.consultaDtoToUsuarioRol(usuarioRolDTOConsulta);
        try {
            UsuarioRol respuesta = usuarioRolUseCase.guardarUsuarioRol(usuarioRol);
            if (respuesta == null) {
                return new ObjetoRespuesta<UsuarioRolDTORespuesta>(null, "Ocurrió un error al intentar guardar el usuarioRol en el sistema, intente nuevamente");
            }
            return new ObjetoRespuesta<UsuarioRolDTORespuesta>(
                    IUsuarioRolMapper.INSTANCE.usuarioRolToDtoRespuesta(respuesta),
                    "UsuarioRol agregado con éxito en el sistema"
            );
        }catch (ValidationException e){
            return new ObjetoRespuesta<UsuarioRolDTORespuesta>(null, e.getMessage());
        }
    }

    public ObjetoRespuesta<UsuarioRolDTORespuesta> actualizarUsuarioRol(UsuarioRolDTOConsulta usuarioRolDTOConsulta){
        UsuarioRol usuarioRol = IUsuarioRolMapper.INSTANCE.consultaDtoToUsuarioRol(usuarioRolDTOConsulta);
        try {
            UsuarioRol respuesta = usuarioRolUseCase.guardarUsuarioRol(usuarioRol);
            if (respuesta == null) {
                return new ObjetoRespuesta<UsuarioRolDTORespuesta>(null, "Ocurrió un error al actualizar los datos del usuarioRol");
            }
            return new ObjetoRespuesta<UsuarioRolDTORespuesta>(
                    IUsuarioRolMapper.INSTANCE.usuarioRolToDtoRespuesta(respuesta),
                    "UsuarioRol actualizado con éxito"
            );
        }catch (ValidationException e){
            return new ObjetoRespuesta<>(null, e.getMessage());
        }
    }

    public ObjetoRespuesta<Object> eliminarUsuarioRolById(int idUsuarioRol){
        String respuesta = usuarioRolUseCase.eliminarUsuarioRolById(idUsuarioRol);
        return new ObjetoRespuesta<Object>(idUsuarioRol, respuesta);
    }

    public ObjetoRespuesta<List<UsuarioRolDTORespuesta>> obtenerTodosUsuariosRol(){
        Stream<UsuarioRol> usuarioRolStream = usuarioRolUseCase.obtenerTodosUsuariosRol();

        List<UsuarioRolDTORespuesta> usuariosRol = usuarioRolStream
                .map(
                        IUsuarioRolMapper.INSTANCE::usuarioRolToDtoRespuesta
                ).collect(Collectors.toList());

        return new ObjetoRespuesta<List<UsuarioRolDTORespuesta>>(usuariosRol, "Listado");
    }
}
