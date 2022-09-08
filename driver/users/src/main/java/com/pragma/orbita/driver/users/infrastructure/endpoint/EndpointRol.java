package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.RolDTORespuesta;
import com.pragma.orbita.driver.users.application.service.RolService;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    public ResponseEntity<RolDTORespuesta> buscarRolPorId(@NotNull @PathVariable int idRol){
        ObjetoRespuesta<RolDTORespuesta> rol = rolService.buscarRolPorId(idRol);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", rol.getMessage());
        if(rol.getDato() == null){
            return new ResponseEntity<RolDTORespuesta>(null, message ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RolDTORespuesta>(rol.getDato(), message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RolDTORespuesta> guardarRol(@NotNull @RequestBody RolDTOConsulta RolDTOConsulta){
        ObjetoRespuesta<RolDTORespuesta> rol = rolService.guardarRol(RolDTOConsulta);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", rol.getMessage());
        if(rol.getDato() == null){
            return new ResponseEntity<RolDTORespuesta>(null, message ,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<RolDTORespuesta>(rol.getDato(), message, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RolDTORespuesta> actualizarRol(@NotNull @RequestBody RolDTOConsulta RolDTOConsulta){
        ObjetoRespuesta<RolDTORespuesta> rol = rolService.actualizarRol(RolDTOConsulta);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", rol.getMessage());
        if(rol.getDato() == null){
            return new ResponseEntity<RolDTORespuesta>(null, message ,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<RolDTORespuesta>(rol.getDato(), message, HttpStatus.OK);
    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<Object> eliminarRolPorId(@NotNull @PathVariable int idRol){
        ObjetoRespuesta<Object> rol = rolService.eliminarRolById(idRol);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", rol.getMessage());
        if(rol.getDato() == null){
            return new ResponseEntity<Object>(null, message ,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>(rol.getDato(), message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RolDTORespuesta>> buscarRolPorId(){
        ObjetoRespuesta<List<RolDTORespuesta>> rol = rolService.obtenerTodosRol();
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", rol.getMessage());
        if(rol.getDato() == null){
            return new ResponseEntity<List<RolDTORespuesta>>(null, message ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<RolDTORespuesta>>(rol.getDato(), message, HttpStatus.OK);
    }
}
