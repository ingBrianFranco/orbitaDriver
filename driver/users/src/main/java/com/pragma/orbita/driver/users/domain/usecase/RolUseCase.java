package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.repository.IRolRepository;
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
public class RolUseCase {

    private final IRolRepository rolRepository;

    private final Validator validator;

    public Rol getRolById(int idRol){
        if(idRol <= 0){
            return null;
        }
        Optional<Rol> rol = rolRepository.getRolById(idRol);
        if(rol.isEmpty()){
            return null;
        }
        return rol.get();
    }

    public Rol guardarRol(Rol rol) throws ValidationException {
        Set<ConstraintViolation<Rol>> validation = validator.validate(rol);
        if(!validation.isEmpty()){
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            throw new ValidationException(errores.toString());
        }
        Rol respuesta = rolRepository.guardarRol(rol);
        return respuesta;
    }

    public boolean existeRolById(int idRol){
        if(idRol <= 0){
            return false;
        }
        return rolRepository.existeRolById(idRol);
    }

    public String eliminarRolById(int idRol){
        if(idRol <= 0){
            return "Id no válido";
        }
        if(!existeRolById(idRol)){
            return "Este rol no se encuentra registrado en el sistema, nada que eliminar";
        }

        rolRepository.eliminarRolById(idRol);

        if(!existeRolById(idRol)){
            return "Rol eliminado con éxito";
        }else{
            return "Ocurrió un error al eliminar el rol";
        }
    }

    public Stream<Rol> obtenerTodosRol(){
        return rolRepository.obtenerTodosRol();
    }
}
