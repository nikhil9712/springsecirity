package com.Security.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Security.usermanagement.dto.SignResponseDto;
import com.Security.usermanagement.dto.SignUpDto;
import com.Security.usermanagement.security.JWTFilter;
import com.Security.usermanagement.service.SignUpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/public")
public class SignUpController {

	
	@Autowired
	private SignUpService service;

	@PostMapping("/signUp")
	public ResponseEntity<SignResponseDto> signUp(@RequestBody SignUpDto SignUpDto) {
		return service.signUp(SignUpDto);
	}
}
