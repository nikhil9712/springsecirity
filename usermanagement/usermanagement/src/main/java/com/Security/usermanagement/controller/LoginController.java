package com.Security.usermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Security.usermanagement.dto.LoginDto;
import com.Security.usermanagement.service.LoginService;

@RestController
public class LoginController {

	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}

	@PostMapping(value ="/login")
	public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto){
		return loginService.login(loginDto);
	}
}
