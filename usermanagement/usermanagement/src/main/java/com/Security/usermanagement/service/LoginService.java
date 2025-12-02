package com.Security.usermanagement.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatusCode;
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
		dto.setAccessToken(jwtUtils.generateAccessToken(userEntity));
		dto.setRefreshtoken(jwtUtils.generaterefreshToken(userEntity));
		return ResponseEntity.ok(dto);
	}

	public ResponseEntity<Map<String, String>> refreshToken(String refreshToken) {
		Map<String, String> map = new HashMap<>();
		UserEntity userEntity = jwtUtils.validaterefreshToken(refreshToken);
		String accessToken = jwtUtils.generateAccessToken(userEntity);
		String generaterefreshToken = jwtUtils.generaterefreshToken(userEntity);
		map.put("accessToken", accessToken);
		map.put("refreshToken", generaterefreshToken);
		return ResponseEntity.ok(map);
	}
}
