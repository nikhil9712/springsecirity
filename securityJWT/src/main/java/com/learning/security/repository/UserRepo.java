package com.learning.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.security.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	User findByusername(String username); 

}
