package com.pragma.orbita.driver.users.infrastructure.endpoint;

import com.pragma.orbita.driver.users.application.DTOConsulta.UsuarioDTOConsulta;
import com.pragma.orbita.driver.users.application.DTORespuesta.UsuarioDTORespuesta;
import com.pragma.orbita.driver.users.application.service.UsuarioService;
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
@RequestMapping("usuario")
@Validated
@AllArgsConstructor
public class EndpointUsuario {

    private UsuarioService usuarioService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTORespuesta> buscarUsuarioPorId(@NotNull @PathVariable int idUsuario){
        ObjetoRespuesta<UsuarioDTORespuesta> usuario = usuarioService.buscarUsuarioPorId(idUsuario);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuario.getMessage());
        if(usuario.getDato() == null){
            return new ResponseEntity<UsuarioDTORespuesta>(null, message , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UsuarioDTORespuesta>(usuario.getDato(), message, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTORespuesta> guardarUsuario(@NotNull @RequestBody UsuarioDTOConsulta UsuarioDTOConsulta){
        ObjetoRespuesta<UsuarioDTORespuesta> usuario = usuarioService.guardarUsuario(UsuarioDTOConsulta);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuario.getMessage());
        if(usuario.getDato() == null){
            return new ResponseEntity<UsuarioDTORespuesta>(null, message ,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UsuarioDTORespuesta>(usuario.getDato(), message, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTORespuesta> actualizarUsuario(@NotNull @RequestBody UsuarioDTOConsulta UsuarioDTOConsulta){
        ObjetoRespuesta<UsuarioDTORespuesta> usuario = usuarioService.actualizarUsuario(UsuarioDTOConsulta);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuario.getMessage());
        if(usuario.getDato() == null){
            return new ResponseEntity<UsuarioDTORespuesta>(null, message ,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UsuarioDTORespuesta>(usuario.getDato(), message, HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Object> eliminarUsuarioPorId(@NotNull @PathVariable int idUsuario){
        ObjetoRespuesta<Object> usuario = usuarioService.eliminarUsuarioById(idUsuario);
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuario.getMessage());
        if(usuario.getDato() == null){
            return new ResponseEntity<Object>(null, message ,HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>(usuario.getDato(), message, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTORespuesta>> buscarUsuarioPorId(){
        ObjetoRespuesta<List<UsuarioDTORespuesta>> usuario = usuarioService.obtenerTodosUsuarios();
        final MultiValueMap<String, String> message = new LinkedMultiValueMap<>();
        message.add("Mensaje", usuario.getMessage());
        if(usuario.getDato() == null){
            return new ResponseEntity<List<UsuarioDTORespuesta>>(null, message ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<UsuarioDTORespuesta>>(usuario.getDato(), message, HttpStatus.OK);
    }
}
