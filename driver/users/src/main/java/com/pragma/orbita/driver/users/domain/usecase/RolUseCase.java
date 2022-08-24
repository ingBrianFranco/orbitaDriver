package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Rol;
import com.pragma.orbita.driver.users.domain.repository.IRolRepository;
import com.pragma.orbita.driver.users.domain.respuesta.ObjetoRespuestaDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class RolUseCase {

    private IRolRepository rolRepository;

    private Validator validator;

    public ObjetoRespuestaDomain<Rol> getRolById(int idRol){
        if(idRol <= 0){
            return new ObjetoRespuestaDomain<Rol>(null, "Id no válido");
        }
        Optional<Rol> Rol = rolRepository.getRolById(idRol);
        String msj = "Rol encontrado";
        if(Rol.isEmpty()){
            msj = "La rol no existe en el sistema";
            return new ObjetoRespuestaDomain<Rol>(null, msj);
        }
        return new ObjetoRespuestaDomain<Rol>(Rol.get(), msj);
    }

    public ObjetoRespuestaDomain<Rol> guardarRol(Rol rol){
        Set<ConstraintViolation<Rol>> validation = validator.validate(rol);
        if(!validation.isEmpty()){
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            return new ObjetoRespuestaDomain<Rol>(null, errores.toString());
        }
        Rol respuesta = rolRepository.guardarRol(rol);
        if(respuesta == null){
            return new ObjetoRespuestaDomain<Rol>(null, "Ocurrió un error al guardar el rol");
        }
        return new ObjetoRespuestaDomain<Rol>(respuesta, "Rol agregado con éxito, id: " + respuesta.getIdRol());
    }

    public boolean existeRolById(int idRol){
        if(idRol <= 0){
            return false;
        }
        return rolRepository.existeRolById(idRol);
    }

    public ObjetoRespuestaDomain<Object> eliminarRolById(int idRol){
        if(idRol <= 0){
            return new ObjetoRespuestaDomain<Object>(null, "Id no válido");
        }
        if(!existeRolById(idRol)){
            return new ObjetoRespuestaDomain<Object>(idRol, "Esta categoría no se encuentra registrada en el sistema, nada que eliminar");
        }

        rolRepository.eliminarRolById(idRol);

        if(!existeRolById(idRol)){
            return new ObjetoRespuestaDomain<Object>(idRol, "Categoría eliminada con éxito");
        }else{
            return new ObjetoRespuestaDomain<Object>(idRol, "Ocurrió un error al eliminar la categoría");
        }
    }

    public ObjetoRespuestaDomain<Stream<Rol>> obtenerTodosRol(){
        return new ObjetoRespuestaDomain<Stream<Rol>>(
                rolRepository.obtenerTodosRol(),
                "Listado"
        );
    }
}
