package com.Security.usermanagement.exceptionHandler;

import java.nio.file.AccessDeniedException;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Security.usermanagement.dto.ApiError;
import com.Security.usermanagement.dto.Response;

@RestControllerAdvice
public class GlobalExceptionhandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		ApiError apiError = new ApiError("Username not found with username: " + ex.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
		ApiError apiError = new ApiError("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
		ApiError apiError = new ApiError("Access denied: Insufficient permissions", HttpStatus.FORBIDDEN);
		return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGenericException(Exception ex) {
		ApiError apiError = new ApiError("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
