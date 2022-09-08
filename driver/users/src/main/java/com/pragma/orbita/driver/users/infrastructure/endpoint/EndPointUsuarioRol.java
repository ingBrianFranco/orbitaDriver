package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.RolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioRolDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioRolDTORespuesta;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import com.pragma.orbita.driver.users.application.service.UsuarioRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("usuariorol")
@Validated
@RequiredArgsConstructor
public class EndPointUsuarioRol {

    private final UsuarioRolService usuarioRolService;

    @GetMapping("/{idUsuarioRol}")
    public ResponseEntity<UsuarioRolDTORespuesta> buscarUsuarioRolPorId(@NotNull @PathVariable int idUsuarioRol){
        ObjetoRespuesta<UsuarioRolDTORespuesta> usuarioRol = usuarioRolService.buscarUsuarioRolPorId(idUsuarioRol);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuarioRol.getMessage());
        if(usuarioRol.getDato() == null){
            return new ResponseEntity<UsuarioRolDTORespuesta>(null, message , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UsuarioRolDTORespuesta>(usuarioRol.getDato(), message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioRolDTORespuesta> guardarUsuarioRol(@NotNull @RequestBody UsuarioRolDTOConsulta usuarioRolDTOConsulta){
        ObjetoRespuesta<UsuarioRolDTORespuesta> usuarioRol = usuarioRolService.guardarUsarioRol(usuarioRolDTOConsulta);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuarioRol.getMessage());
        if(usuarioRol.getDato() == null){
            return new ResponseEntity<UsuarioRolDTORespuesta>(null, message ,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UsuarioRolDTORespuesta>(usuarioRol.getDato(), message, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UsuarioRolDTORespuesta> actualizarUsuarioRol(@NotNull @RequestBody UsuarioRolDTOConsulta usuarioRolDTOConsulta){
        ObjetoRespuesta<UsuarioRolDTORespuesta> usuarioRol = usuarioRolService.actualizarUsuarioRol(usuarioRolDTOConsulta);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuarioRol.getMessage());
        if(usuarioRol.getDato() == null){
            return new ResponseEntity<UsuarioRolDTORespuesta>(null, message ,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UsuarioRolDTORespuesta>(usuarioRol.getDato(), message, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuarioRol}")
    public ResponseEntity<Object> eliminarUsuarioRolPorId(@NotNull @PathVariable int idUsuarioRol){
        ObjetoRespuesta<Object> usuarioRol = usuarioRolService.eliminarUsuarioRolById(idUsuarioRol);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuarioRol.getMessage());
        if(usuarioRol.getDato() == null){
            return new ResponseEntity<Object>(null, message ,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>(usuarioRol.getDato(), message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRolDTORespuesta>> buscarTodosUsuariosRol(){
        ObjetoRespuesta<List<UsuarioRolDTORespuesta>> usuariosRol = usuarioRolService.obtenerTodosUsuariosRol();
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuariosRol.getMessage());
        if(usuariosRol.getDato() == null){
            return new ResponseEntity<List<UsuarioRolDTORespuesta>>(null, message ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<UsuarioRolDTORespuesta>>(usuariosRol.getDato(), message, HttpStatus.OK);
    }
}
