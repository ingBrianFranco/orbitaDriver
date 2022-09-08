package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDTORespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IUsuarioMapper;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.domain.usecase.UsuarioUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioUseCase usuarioUseCase;

    public ObjetoRespuesta<UsuarioDTORespuesta> buscarUsuarioPorId(int idUsuario){
        Usuario respuesta = usuarioUseCase.getUsuarioById(idUsuario);

        if(respuesta == null){
            return new ObjetoRespuesta<UsuarioDTORespuesta>(null, "El usuario no existe en el sistema");
        }
        UsuarioDTORespuesta UsuarioDTORespuesta = IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta);

        return new ObjetoRespuesta<UsuarioDTORespuesta>(UsuarioDTORespuesta, "Usuario encontrado");
    }

    public ObjetoRespuesta<UsuarioDTORespuesta> guardarUsuario(UsuarioDTOConsulta UsuarioDTOConsulta){
        Usuario usuario = IUsuarioMapper.INSTANCE.consultaDtoToUsuario(UsuarioDTOConsulta);
        try {
            Usuario respuesta = usuarioUseCase.guardarUsuario(usuario);
            if (respuesta == null) {
                return new ObjetoRespuesta<UsuarioDTORespuesta>(null, "Ocurrió un error al intentar guardar el usuario");
            }
            return new ObjetoRespuesta<UsuarioDTORespuesta>(
                    IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta),
                    "Usuario añadido con éxito al sistema"
            );
        }catch (ValidationException e){
            return new ObjetoRespuesta<>(null, e.getMessage());
        }
    }

    public ObjetoRespuesta<UsuarioDTORespuesta> actualizarUsuario(UsuarioDTOConsulta UsuarioDTOConsulta){
        Usuario Usuario = IUsuarioMapper.INSTANCE.consultaDtoToUsuario(UsuarioDTOConsulta);
        try {
            Usuario respuesta = usuarioUseCase.guardarUsuario(Usuario);
            if (respuesta == null) {
                return new ObjetoRespuesta<UsuarioDTORespuesta>(null, "Ocurrió un error al actualizar los datos del usuario");
            }
            return new ObjetoRespuesta<UsuarioDTORespuesta>(
                    IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta),
                    "Usuario actualizado con éxito"
            );
        }catch (ValidationException e){
            return new ObjetoRespuesta<>(null, e.getMessage());
        }
    }

    public ObjetoRespuesta<Object> eliminarUsuarioById(int idUsuario){
        String respuesta = usuarioUseCase.eliminarUsuarioById(idUsuario);
        return new ObjetoRespuesta<Object>(idUsuario, respuesta);
    }

    public ObjetoRespuesta<List<UsuarioDTORespuesta>> obtenerTodosUsuarios(){
        Stream<Usuario> UsuarioStream = usuarioUseCase.obtenerTodosUsuarios();

        List<UsuarioDTORespuesta> Usuarios = UsuarioStream
                .map(
                        IUsuarioMapper.INSTANCE::usuarioToDtoRespuesta
                ).collect(Collectors.toList());

        return new ObjetoRespuesta<List<UsuarioDTORespuesta>>(Usuarios, "Listado");
    }
}
