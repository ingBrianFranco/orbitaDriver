package com.pragma.orbita.driver.users.infrastructure.persistence.repositoryImpl;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRepository;
import com.pragma.orbita.driver.users.infrastructure.persistence.DAO.IUsuarioDao;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioEntity;
import com.pragma.orbita.driver.users.infrastructure.persistence.mapper.IMapperUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class UsuarioRepositoryImpl implements IUsuarioRepository {
    
    private IUsuarioDao usuarioDao;


    @Override
    public Optional<Usuario> getUsuarioById(int idUsuario) {
        Optional<UsuarioEntity> respuesta = usuarioDao.findById(idUsuario);
        if(respuesta.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(
                IMapperUsuarioRepository
                        .INSTANCE.entityToUsuario(
                                respuesta.get()
                        )
        );
    }

    @Override
    public Usuario guardarUsuario(Usuario Usuario) {
        UsuarioEntity UsuarioEntity = IMapperUsuarioRepository.INSTANCE.usuarioToEntity(Usuario);
        return IMapperUsuarioRepository
                .INSTANCE.entityToUsuario(
                        usuarioDao.save(UsuarioEntity)
                );
    }

    @Override
    public Boolean existeUsuarioById(int idUsuario) {
        return usuarioDao.existsById(idUsuario);
    }

    @Override
    public void eliminarUsuarioById(int idUsuario) {
        usuarioDao.deleteById(idUsuario);
    }

    @Override
    public Stream<Usuario> obtenerTodosUsuarios() {
        Iterable<UsuarioEntity> UsuarioEntities = usuarioDao.findAll();
        List<Usuario> Usuarios = new ArrayList<Usuario>();
        for (UsuarioEntity c:UsuarioEntities) {
            Usuarios.add(
                    IMapperUsuarioRepository
                            .INSTANCE.entityToUsuario(c)
            );
        }
        return Usuarios.stream();
    }
}
