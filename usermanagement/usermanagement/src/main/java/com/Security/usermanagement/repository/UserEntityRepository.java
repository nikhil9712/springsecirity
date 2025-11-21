package com.Security.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Security.usermanagement.entity.UserEntity;
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUserName(String userName);

}
