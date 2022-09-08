package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRepository;
import com.pragma.orbita.driver.users.application.respuesta.ObjetoRespuesta;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;
    private final Validator validator;

    public Usuario getUsuarioById(int idUsuario){
        if(idUsuario <= 0){
            return null;
        }
        Optional<Usuario> usuario = usuarioRepository.getUsuarioById(idUsuario);
        if(usuario.isEmpty()){
            return null;
        }
        return usuario.get();
    }

    public Usuario guardarUsuario(Usuario Usuario) throws ValidationException {
        Set<ConstraintViolation<Usuario>> validation = validator.validate(Usuario);
        if(!validation.isEmpty()){
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            throw new ValidationException(errores.toString());
        }
        Usuario respuesta = usuarioRepository.guardarUsuario(Usuario);
        return respuesta;
    }

    public boolean existeUsuarioById(int idUsuario){
        if(idUsuario <= 0){
            return false;
        }
        return usuarioRepository.existeUsuarioById(idUsuario);
    }

    public String eliminarUsuarioById(int idUsuario){
        if(idUsuario <= 0){
            return "Id no válido";
        }
        if(!existeUsuarioById(idUsuario)){
            return "Este usuario no se encuentra registrado en el sistema, nada que eliminar";
        }

        usuarioRepository.eliminarUsuarioById(idUsuario);

        if(!existeUsuarioById(idUsuario)){
            return "Usuario eliminado con éxito";
        }else{
            return "Ocurrió un error al intentar eliminar el usuario";
        }
    }

    public Stream<Usuario> obtenerTodosUsuarios(){
        return usuarioRepository.obtenerTodosUsuarios();
    }

}
