package com.Security.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.Security.usermanagement.dto.LoginDto;
import com.Security.usermanagement.dto.LoginResponse;
import com.Security.usermanagement.entity.UserEntity;
import com.Security.usermanagement.utils.JWTUtils;

@Service
public class LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtils jwtUtils;

	public ResponseEntity<LoginResponse> login(LoginDto loginDto) {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		UserEntity userEntity = (UserEntity) authenticate.getPrincipal();
		System.out.println(userEntity.getUsername());
		LoginResponse dto = new LoginResponse();
		dto.setAccessToken(jwtUtils.generateJwt(userEntity));
		return ResponseEntity.ok(dto);
	}
}
