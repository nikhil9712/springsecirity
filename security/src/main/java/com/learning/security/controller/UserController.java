package com.learning.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.security.entity.User;
import com.learning.security.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/registerUser")
	public User registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@DeleteMapping("/deleteUsers")
	public String deleteUsers() {
		 userService.deleteUsers();
		 return "users Deleted Successfully";
	}
}
