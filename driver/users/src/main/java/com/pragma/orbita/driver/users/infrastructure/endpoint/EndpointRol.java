package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDTORespuesta;
import com.pragma.orbita.driver.users.application.service.RolService;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
import com.pragma.orbita.driver.users.infrastructure.respuesta.ObjetoRespuestaInfrastructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("rol")
@Validated
@AllArgsConstructor
public class EndpointRol {
    
    private RolService rolService;

    @GetMapping("/{idRol}")
    public ObjetoRespuestaInfrastructure<RolDTORespuesta> buscarRolPorId(@NotNull @PathVariable int idRol){
        ObjetoRespuestaDomain<RolDTORespuesta> Rol = rolService.buscarRolPorId(idRol);
        if(Rol.getDato() == null){
            return new ObjetoRespuestaInfrastructure<RolDTORespuesta>(HttpStatus.NOT_FOUND, null, Rol.getMessage());
        }
        return new ObjetoRespuestaInfrastructure<RolDTORespuesta>(HttpStatus.FOUND, Rol.getDato(), Rol.getMessage());
    }

    @PostMapping("/guardar")
    public ObjetoRespuestaInfrastructure<RolDTORespuesta> guardarRol(@NotNull @RequestBody RolDTOConsulta RolDTOConsulta){
        ObjetoRespuestaDomain<RolDTORespuesta> Rol = rolService.guardarRol(RolDTOConsulta);
        if(Rol.getDato() == null){
            return new ObjetoRespuestaInfrastructure<RolDTORespuesta>(HttpStatus.CONFLICT, null, Rol.getMessage());
        }
        return new ObjetoRespuestaInfrastructure<RolDTORespuesta>(HttpStatus.CREATED, Rol.getDato(), Rol.getMessage());
    }

    @PutMapping("/actualizar")
    public ObjetoRespuestaInfrastructure<RolDTORespuesta> actualizarRol(@NotNull @RequestBody RolDTOConsulta RolDTOConsulta){
        ObjetoRespuestaDomain<RolDTORespuesta> Rol = rolService.actualizarRol(RolDTOConsulta);
        if(Rol.getDato() == null){
            return new ObjetoRespuestaInfrastructure<RolDTORespuesta>(HttpStatus.CONFLICT, null, Rol.getMessage());
        }
        return new ObjetoRespuestaInfrastructure<RolDTORespuesta>(HttpStatus.OK, Rol.getDato(), Rol.getMessage());
    }

    @PutMapping("/eliminar/{idRol}")
    public ObjetoRespuestaInfrastructure<Object> eliminarRolPorId(@NotNull @PathVariable int idRol){
        ObjetoRespuestaDomain<Object> Rol = rolService.eliminarRolById(idRol);
        if(Rol.getDato() == null){
            return new ObjetoRespuestaInfrastructure<Object>(HttpStatus.CONFLICT, null, Rol.getMessage());
        }
        return new ObjetoRespuestaInfrastructure<Object>(HttpStatus.OK, Rol.getDato(), Rol.getMessage());
    }

    @GetMapping("/todas")
    public ObjetoRespuestaInfrastructure<List<RolDTORespuesta>> buscarRolPorId(){
        ObjetoRespuestaDomain<List<RolDTORespuesta>> Rol = rolService.obtenerTodosRol();
        if(Rol.getDato() == null){
            return new ObjetoRespuestaInfrastructure<List<RolDTORespuesta>>(HttpStatus.NOT_FOUND, null, Rol.getMessage());
        }
        return new ObjetoRespuestaInfrastructure<List<RolDTORespuesta>>(HttpStatus.FOUND, Rol.getDato(), Rol.getMessage());
    }
}
