package com.gorillaz.clients.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptions {
	@ExceptionHandler({ Exception.class, ClientException.class })
	public ResponseEntity<?> handledExceptions(Exception ex) {
		ResponseEntity<?> response = null;
		if (ex instanceof ClientException) {
			response = new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
