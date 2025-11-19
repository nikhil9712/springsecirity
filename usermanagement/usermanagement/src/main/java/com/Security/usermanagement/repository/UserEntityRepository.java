package com.Security.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Security.usermanagement.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUserName(String userName);

}
