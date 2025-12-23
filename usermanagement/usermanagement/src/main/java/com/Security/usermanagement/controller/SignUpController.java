package com.Security.usermanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	 @GetMapping("/reports")
	    public ResponseEntity<Map<String, Object>> getReports() {
	        Map<String, Object> response = Map.of(
	            "status", "SUCCESS",
	            "message", "Admin report fetched successfully",
	            "totalReports", 5
	        );
	        return ResponseEntity.ok(response);
	    }
}
