package com.pragma.orbita.driver.users.infrastructure.persistence.DAO;

import com.pragma.orbita.driver.users.infrastructure.persistence.entity.RolEntity;
import org.springframework.data.repository.CrudRepository;

public interface IRolDao extends CrudRepository<RolEntity, Integer> {
}
