package com.Security.usermanagement.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Security.usermanagement.dto.LoginDto;
import com.Security.usermanagement.dto.LoginResponse;
import com.Security.usermanagement.service.LoginService;

@RestController
public class LoginController {

	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}

	@PostMapping(value ="/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto){
		return loginService.login(loginDto);
	}
	
	@PostMapping(value ="/refreshToken")
	public ResponseEntity<Map<String, String>> RefreshToken( @RequestHeader("Refresh-Token") String refreshToken){
		return loginService.refreshToken(refreshToken);
	}
}
