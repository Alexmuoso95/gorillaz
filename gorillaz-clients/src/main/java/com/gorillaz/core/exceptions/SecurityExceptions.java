package com.gorillaz.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebMvc
@RestControllerAdvice
public class SecurityExceptions {
	@ExceptionHandler({ BadCredentialsException.class })
	public ResponseEntity<?> handledBadRequestExceptions(BadCredentialsException ex) {
		log.error(":::::: /authenticate - Security Exception  :::" , ex);
		return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
	}
}
