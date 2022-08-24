package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDTORespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IRolMapper;
import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
import com.pragma.orbita.driver.users.domain.usecase.RolUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class RolService {
    
    private RolUseCase rolUseCase;

    public ObjetoRespuestaDomain<RolDTORespuesta> buscarRolPorId(int idRol){
        ObjetoRespuestaDomain<Rol> respuesta = rolUseCase.getRolById(idRol);

        if(respuesta.getDato() == null){
            return new ObjetoRespuestaDomain<RolDTORespuesta>(null, respuesta.getMessage());
        }

        RolDTORespuesta RolDTORespuesta = IRolMapper.INSTANCE.rolToDtoRespuesta(respuesta.getDato());

        return new ObjetoRespuestaDomain<RolDTORespuesta>(RolDTORespuesta, respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<RolDTORespuesta> guardarRol(RolDTOConsulta RolDTOConsulta){
        Rol Rol = IRolMapper.INSTANCE.consultaDtoToRol(RolDTOConsulta);
        ObjetoRespuestaDomain<Rol> respuesta = rolUseCase.guardarRol(Rol);
        if(respuesta.getDato() == null){
            return new ObjetoRespuestaDomain<RolDTORespuesta>(null, respuesta.getMessage());
        }
        return new ObjetoRespuestaDomain<RolDTORespuesta>(
                IRolMapper.INSTANCE.rolToDtoRespuesta(respuesta.getDato()),
                respuesta.getMessage()
        );
    }

    public ObjetoRespuestaDomain<RolDTORespuesta> actualizarRol(RolDTOConsulta RolDTOConsulta){
        Rol Rol = IRolMapper.INSTANCE.consultaDtoToRol(RolDTOConsulta);
        ObjetoRespuestaDomain<Rol> respuesta = rolUseCase.guardarRol(Rol);
        if(respuesta.getDato() == null){
            return new ObjetoRespuestaDomain<RolDTORespuesta>(null, "Ocurrió un error al actualizar los datos del rol");
        }
        return new ObjetoRespuestaDomain<RolDTORespuesta>(
                IRolMapper.INSTANCE.rolToDtoRespuesta(respuesta.getDato()),
                "Rol actualizado con éxito"
        );
    }

    public ObjetoRespuestaDomain<Object> eliminarRolById(int idRol){
        ObjetoRespuestaDomain<Object> respuesta = rolUseCase.eliminarRolById(idRol);

        if(respuesta.getDato() == null){
            return new ObjetoRespuestaDomain<Object>(null, respuesta.getMessage());
        }
        return new ObjetoRespuestaDomain<Object>(idRol, respuesta.getMessage());
    }

    public ObjetoRespuestaDomain<List<RolDTORespuesta>> obtenerTodosRol(){
        Stream<Rol> RolStream = rolUseCase.obtenerTodosRol().getDato();

        List<RolDTORespuesta> Rols = RolStream
                .map(
                        IRolMapper.INSTANCE::rolToDtoRespuesta
                ).collect(Collectors.toList());

        return new ObjetoRespuestaDomain<List<RolDTORespuesta>>(Rols, "Listado");
    }
}
