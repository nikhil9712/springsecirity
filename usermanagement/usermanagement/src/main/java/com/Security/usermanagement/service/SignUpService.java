package com.Security.usermanagement.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.Security.usermanagement.dto.SignResponseDto;
import com.Security.usermanagement.dto.SignUpDto;
import com.Security.usermanagement.entity.UserEntity;
import com.Security.usermanagement.repository.UserEntityRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SignUpService {

	@Autowired
	private UserEntityRepository entityRepository;

	public ResponseEntity<SignResponseDto> signUp(SignUpDto signUpDto) {
		SignResponseDto signResponseDto = new SignResponseDto();
		try {
			UserEntity existingEntity = entityRepository.findByUserName(signUpDto.getUserName());
			if (Objects.nonNull(existingEntity)) {
				signResponseDto.setMessage(String.format("User Already Exists with name %s", signUpDto.getUserName()));
				return ResponseEntity.status(HttpStatus.CONFLICT).body(signResponseDto);
			}
			UserEntity entity = new UserEntity();
			entity.setUserName(signUpDto.getUserName());
			entity.setPassword(signUpDto.getPassword());
			entityRepository.save(entity);
			signResponseDto.setMessage("Successfully Sign up");
			return ResponseEntity.ok(signResponseDto);

		} catch (Exception e) {
			signResponseDto.setMessage("Internal Server Error");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(signResponseDto);
		}
	}

}
