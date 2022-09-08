package com.pragma.orbita.driver.users.infrastructure.persistence.repositoryImpl;

import com.pragma.orbita.driver.users.domain.model.Usuario;
import com.pragma.orbita.driver.users.domain.model.UsuarioRol;
import com.pragma.orbita.driver.users.domain.repository.IUsuarioRolRepository;
import com.pragma.orbita.driver.users.infrastructure.persistence.DAO.IUsuarioRolDao;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioEntity;
import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioRolEntity;
import com.pragma.orbita.driver.users.infrastructure.persistence.mapper.IMapperUsuarioRepository;
import com.pragma.orbita.driver.users.infrastructure.persistence.mapper.IMapperUsuarioRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class UsuarioRolRepositoryImpl implements IUsuarioRolRepository {

    private final IUsuarioRolDao usuarioRolDao;
    @Override
    public Optional<UsuarioRol> getUsuarioRolById(int idUsuarioRol) {
        Optional<UsuarioRolEntity> respuesta = usuarioRolDao.findById(idUsuarioRol);
        if(respuesta.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(
                IMapperUsuarioRolRepository
                        .INSTANCE.entityToUsuarioRol(
                                respuesta.get()
                        )
        );
    }

    @Override
    public UsuarioRol guardarUsuarioRol(UsuarioRol usuarioRol) {
        UsuarioRolEntity usuarioRolEntity = IMapperUsuarioRolRepository.INSTANCE.usuarioRolToEntity(usuarioRol);
        return IMapperUsuarioRolRepository
                .INSTANCE.entityToUsuarioRol(
                        usuarioRolDao.save(usuarioRolEntity)
                );
    }

    @Override
    public Boolean existeUsuarioRolById(int idUsuarioRol) {
        return usuarioRolDao.existsById(idUsuarioRol);
    }

    @Override
    public void eliminarUsuarioRolById(int idUsuarioRol) { usuarioRolDao.deleteById(idUsuarioRol);}

    @Override
    public Stream<UsuarioRol> obtenerTodosUsuariosRol() {
        Iterable<UsuarioRolEntity> usuarioRolEntities = usuarioRolDao.findAll();
        List<UsuarioRol> usuariosRol = new ArrayList<UsuarioRol>();
        for (UsuarioRolEntity u:usuarioRolEntities) {
            usuariosRol.add(
                    IMapperUsuarioRolRepository
                            .INSTANCE.entityToUsuarioRol(u)
            );
        }
        return usuariosRol.stream();
    }
}
