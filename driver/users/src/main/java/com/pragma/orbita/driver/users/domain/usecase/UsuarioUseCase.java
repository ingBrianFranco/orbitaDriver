package com.pragma.orbita.driver.users.domain.usecase;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRepository;
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
public class UsuarioUseCase {

    private IUsuarioRepository usuarioRepository;
    private Validator validator;

    public ObjetoRespuestaDomain<Usuario> getUsuarioById(int idUsuario){
        if(idUsuario <= 0){
            return new ObjetoRespuestaDomain<Usuario>(null, "Id no válido");
        }
        Optional<Usuario> Usuario = usuarioRepository.getUsuarioById(idUsuario);
        String msj = "Usuario encontrada";
        if(Usuario.isEmpty()){
            msj = "La Usuario no existe en el sistema";
            return new ObjetoRespuestaDomain<Usuario>(null, msj);
        }
        return new ObjetoRespuestaDomain<Usuario>(Usuario.get(), msj);
    }

    public ObjetoRespuestaDomain<Usuario> guardarUsuario(Usuario Usuario){
        Set<ConstraintViolation<Usuario>> validation = validator.validate(Usuario);
        if(!validation.isEmpty()){
            StringBuilder errores = new StringBuilder("Datos no válidos.");
            errores.append("Errores: ");
            validation.forEach(x -> {
                errores.append(x.getMessage());
                errores.append(", ");
            });
            return new ObjetoRespuestaDomain<Usuario>(null, errores.toString());
        }
        Usuario respuesta = usuarioRepository.guardarUsuario(Usuario);
        if(respuesta == null){
            return new ObjetoRespuestaDomain<Usuario>(null, "Ocurrió un error al guardar la categoría");
        }
        return new ObjetoRespuestaDomain<Usuario>(respuesta, "Usuario agregada con éxito, id: " + respuesta.getIdUsuario());
    }

    public boolean existeUsuarioById(int idUsuario){
        if(idUsuario <= 0){
            return false;
        }
        return usuarioRepository.existeUsuarioById(idUsuario);
    }

    public ObjetoRespuestaDomain<Object> eliminarUsuarioById(int idUsuario){
        if(idUsuario <= 0){
            return new ObjetoRespuestaDomain<Object>(null, "Id no válido");
        }
        if(!existeUsuarioById(idUsuario)){
            return new ObjetoRespuestaDomain<Object>(idUsuario, "Esta categoría no se encuentra registrada en el sistema, nada que eliminar");
        }

        usuarioRepository.eliminarUsuarioById(idUsuario);

        if(!existeUsuarioById(idUsuario)){
            return new ObjetoRespuestaDomain<Object>(idUsuario, "Categoría eliminada con éxito");
        }else{
            return new ObjetoRespuestaDomain<Object>(idUsuario, "Ocurrió un error al eliminar la categoría");
        }
    }

    public ObjetoRespuestaDomain<Stream<Usuario>> obtenerTodasUsuarios(){
        return new ObjetoRespuestaDomain<Stream<Usuario>>(
                usuarioRepository.obtenerTodosUsuarios(),
                "Listado"
        );
    }

}
