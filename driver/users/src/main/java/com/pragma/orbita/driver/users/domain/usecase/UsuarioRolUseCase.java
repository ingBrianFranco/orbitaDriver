package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRolRepository;
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
public class UsuarioRolUseCase {

    private final IUsuarioRolRepository usuarioRolRepository;

    private final Validator validator;

    public UsuarioRol getUsuarioRolById(int idUsuarioRol){
        if(idUsuarioRol <= 0){
            return null;
        }
        Optional<UsuarioRol> usuarioRol = usuarioRolRepository.getUsuarioRolById(idUsuarioRol);
        if(usuarioRol.isEmpty()){
            return null;
        }
        return usuarioRol.get();
    }

    public UsuarioRol guardarUsuarioRol(UsuarioRol usuarioRol) throws ValidationException {
        Set<ConstraintViolation<UsuarioRol>> validation = validator.validate(usuarioRol);
        if(!validation.isEmpty()){
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            throw new ValidationException(errores.toString());
        }
        UsuarioRol respuesta = usuarioRolRepository.guardarUsuarioRol(usuarioRol);
        return respuesta;
    }

    public boolean existeUsuarioRolById(int idUsuarioRol){
        if(idUsuarioRol <= 0){
            return false;
        }
        return usuarioRolRepository.existeUsuarioRolById(idUsuarioRol);
    }

    public String eliminarUsuarioRolById(int idUsuarioRol){
        if(idUsuarioRol <= 0){
            return "Id no válido";
        }
        if(!existeUsuarioRolById(idUsuarioRol)){
            return "Este usuarioRol no se encuentra registrado en el sistema, nada que eliminar";
        }

        usuarioRolRepository.eliminarUsuarioRolById(idUsuarioRol);

        if(!existeUsuarioRolById(idUsuarioRol)){
            return "UsuarioRol eliminado con éxito";
        }else{
            return "Ocurrió un error al eliminar el UsuarioRol";
        }
    }

    public Stream<UsuarioRol> obtenerTodosUsuariosRol(){
        return usuarioRolRepository.obtenerTodosUsuariosRol();
    }
}
