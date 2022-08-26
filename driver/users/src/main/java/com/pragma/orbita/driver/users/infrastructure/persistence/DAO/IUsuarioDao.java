package com.pragma.orbita.driver.users.infrastructure.persistence.DAO;

import com.pragma.orbita.driver.users.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<UsuarioEntity, Integer> {
}
