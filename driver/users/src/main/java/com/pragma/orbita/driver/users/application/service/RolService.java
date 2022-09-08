package com.pragma.orbita.driver.users.application.service;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDTORespuesta;
import com.pragma.orbita.driver.users.application.mapper.mapInterface.IRolMapper;
import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.domain.usecase.RolUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RolService {
    
    private RolUseCase rolUseCase;

    public ObjetoRespuesta<RolDTORespuesta> buscarRolPorId(int idRol){
        Rol respuesta = rolUseCase.getRolById(idRol);

        if(respuesta == null){
            return new ObjetoRespuesta<RolDTORespuesta>(null, "El rol no existe en el sistema");
        }

        RolDTORespuesta RolDTORespuesta = IRolMapper.INSTANCE.rolToDtoRespuesta(respuesta);

        return new ObjetoRespuesta<RolDTORespuesta>(RolDTORespuesta, "Rol encontrado");
    }

    public ObjetoRespuesta<RolDTORespuesta> guardarRol(RolDTOConsulta RolDTOConsulta){
        Rol rol = IRolMapper.INSTANCE.consultaDtoToRol(RolDTOConsulta);
        try {
            Rol respuesta = rolUseCase.guardarRol(rol);
            if (respuesta == null) {
                return new ObjetoRespuesta<RolDTORespuesta>(null, "Ocurrió un error al intentar guardar el rol en el sistema, intente nuevamente");
            }
            return new ObjetoRespuesta<RolDTORespuesta>(
                    IRolMapper.INSTANCE.rolToDtoRespuesta(respuesta),
                    "Rol agregado con éxito en el sistema"
            );
        }catch (ValidationException e){
            return new ObjetoRespuesta<RolDTORespuesta>(null, e.getMessage());
        }
    }

    public ObjetoRespuesta<RolDTORespuesta> actualizarRol(RolDTOConsulta RolDTOConsulta){
        Rol rol = IRolMapper.INSTANCE.consultaDtoToRol(RolDTOConsulta);
        try {
            Rol respuesta = rolUseCase.guardarRol(rol);
            if (respuesta == null) {
                return new ObjetoRespuesta<RolDTORespuesta>(null, "Ocurrió un error al actualizar los datos del rol");
            }
            return new ObjetoRespuesta<RolDTORespuesta>(
                    IRolMapper.INSTANCE.rolToDtoRespuesta(respuesta),
                    "Rol actualizado con éxito"
            );
        }catch (ValidationException e){
            return new ObjetoRespuesta<>(null, e.getMessage());
        }
    }

    public ObjetoRespuesta<Object> eliminarRolById(int idRol){
        String respuesta = rolUseCase.eliminarRolById(idRol);
        return new ObjetoRespuesta<Object>(idRol, respuesta);
    }

    public ObjetoRespuesta<List<RolDTORespuesta>> obtenerTodosRol(){
        Stream<Rol> RolStream = rolUseCase.obtenerTodosRol();

        List<RolDTORespuesta> Rols = RolStream
                .map(
                        IRolMapper.INSTANCE::rolToDtoRespuesta
                ).collect(Collectors.toList());

        return new ObjetoRespuesta<List<RolDTORespuesta>>(Rols, "Listado");
    }
}
