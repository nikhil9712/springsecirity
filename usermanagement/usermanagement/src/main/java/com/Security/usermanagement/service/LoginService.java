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

@Service
public class LoginService {

	@Autowired
	AuthenticationManager authenticationManager;

	public ResponseEntity<LoginDto> login(LoginDto loginDto) {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
		User user = (User) authenticate.getPrincipal();

		LoginDto dto = new LoginDto();
		dto.setUserName(user.getUsername());
		dto.setAccessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.\r\n"
				+ "eyJzdWIiOiJuaWtoaWwiLCJyb2xlcyI6WyJVU0VSIl0sImlhdCI6MTY5MDAwMDAwMCwiZXhwIjoxNjkwMDAzNjAwfQ.\r\n"
				+ "dummySignature1234567890abcdef\r\n"
				+ "");
		return ResponseEntity.ok(dto);
	}
}
