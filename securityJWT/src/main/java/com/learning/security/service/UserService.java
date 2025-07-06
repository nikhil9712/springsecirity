package com.learning.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.security.entity.User;
import com.learning.security.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

	public User registerUser(User user) {
		try {
			user.setPasseword(bCryptPasswordEncoder.encode(user.getPasseword()));
			User save = userRepo.save(user);
			System.out.println(save);

		} catch (Exception e) {
			System.out.println("Error while saving user");
		}
		return user;
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public void deleteUsers() {
		userRepo.deleteAll();
	}

	public String verify(User user) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPasseword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		} else {
			return "failed";
		}

	}

}
