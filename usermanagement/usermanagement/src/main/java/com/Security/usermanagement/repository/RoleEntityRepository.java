package com.Security.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Security.usermanagement.entity.RoleEntity;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long>{

    Optional<RoleEntity> findByName(String name);

}
