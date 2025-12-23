package com.Security.usermanagement.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.Security.usermanagement.dto.RoleDto;
import com.Security.usermanagement.dto.SignResponseDto;
import com.Security.usermanagement.dto.SignUpDto;
import com.Security.usermanagement.entity.RoleEntity;
import com.Security.usermanagement.entity.UserEntity;
import com.Security.usermanagement.repository.RoleEntityRepository;
import com.Security.usermanagement.repository.UserEntityRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class SignUpService {

	@Autowired
	private UserEntityRepository entityRepository;
	
	@Autowired
	private RoleEntityRepository roleEntityRepository ;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
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
			entity.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
			entity.setCreatedAt(LocalDate.now());
			
			Set<RoleEntity> roles=new HashSet<>();
			// to check the assigned role already exists or not 
			// Need to create a API to save role
			for (RoleDto roleDto : signUpDto.getRoles()) {
			    RoleEntity roleEntity = roleEntityRepository
			            .findByName(roleDto.getName())
			            .orElseThrow(() -> new RuntimeException("Role not found: " + roleDto.getName()));
			    roles.add(roleEntity);
			}

			entity.setRoles(roles);
			entityRepository.save(entity);

			signResponseDto.setMessage("Successfully Sign up");
			return ResponseEntity.ok(signResponseDto);
		} catch (Exception e) {
			signResponseDto.setMessage("Internal Server Error");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(signResponseDto);
		}
	}

}
