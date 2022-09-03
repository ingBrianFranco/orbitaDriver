package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDTORespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IUsuarioMapper;
import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
import com.pragma.orbita.driver.users.domain.usecase.UsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioUseCase usuarioUseCase;

    public ObjetoRespuestaDomain<UsuarioDTORespuesta> buscarUsuarioPorId(int idUsuario){
        ObjetoRespuestaDomain<Usuario> respuesta = usuarioUseCase.getUsuarioById(idUsuario);

        if(respuesta.getDato() == null){
            return new ObjetoRespuestaDomain<UsuarioDTORespuesta>(null, respuesta.getMessage());
        }

        UsuarioDTORespuesta UsuarioDTORespuesta = IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta.getDato());

        return new ObjetoRespuestaDomain<UsuarioDTORespuesta>(UsuarioDTORespuesta, respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<UsuarioDTORespuesta> guardarUsuario(UsuarioDTOConsulta UsuarioDTOConsulta){
        Usuario Usuario = IUsuarioMapper.INSTANCE.consultaDtoToUsuario(UsuarioDTOConsulta);
        ObjetoRespuestaDomain<Usuario> respuesta = usuarioUseCase.guardarUsuario(Usuario);
        if(respuesta.getDato() == null){
            return new ObjetoRespuestaDomain<UsuarioDTORespuesta>(null, respuesta.getMessage());
        }
        return new ObjetoRespuestaDomain<UsuarioDTORespuesta>(
                IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta.getDato()),
                respuesta.getMessage()
        );
    }

    public ObjetoRespuestaDomain<UsuarioDTORespuesta> actualizarUsuario(UsuarioDTOConsulta UsuarioDTOConsulta){
        Usuario Usuario = IUsuarioMapper.INSTANCE.consultaDtoToUsuario(UsuarioDTOConsulta);
        ObjetoRespuestaDomain<Usuario> respuesta = usuarioUseCase.guardarUsuario(Usuario);
        if(respuesta.getDato() == null){
            return new ObjetoRespuestaDomain<UsuarioDTORespuesta>(null, "Ocurrió un error al actualizar los datos de la categoría");
        }
        return new ObjetoRespuestaDomain<UsuarioDTORespuesta>(
                IUsuarioMapper.INSTANCE.usuarioToDtoRespuesta(respuesta.getDato()),
                "Categoría actualizada con éxito"
        );
    }

    public ObjetoRespuestaDomain<Object> eliminarUsuarioById(int idUsuario){
        ObjetoRespuestaDomain<Object> respuesta = usuarioUseCase.eliminarUsuarioById(idUsuario);

        if(respuesta.getDato() == null){
            return new ObjetoRespuestaDomain<Object>(null, respuesta.getMessage());
        }
        return new ObjetoRespuestaDomain<Object>(idUsuario, respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<List<UsuarioDTORespuesta>> obtenerTodosUsuarios(){
        Stream<Usuario> UsuarioStream = usuarioUseCase.obtenerTodosUsuarios().getDato();

        List<UsuarioDTORespuesta> Usuarios = UsuarioStream
                .map(
                        IUsuarioMapper.INSTANCE::usuarioToDtoRespuesta
                ).collect(Collectors.toList());

        return new ObjetoRespuestaDomain<List<UsuarioDTORespuesta>>(Usuarios, "Listado");
    }
}
