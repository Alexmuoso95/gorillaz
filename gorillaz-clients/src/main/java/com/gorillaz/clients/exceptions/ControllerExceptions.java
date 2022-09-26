package com.gorillaz.clients.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebMvc
@RestControllerAdvice
public class ControllerExceptions {
//	@ExceptionHandler({ RuntimeException.class })
//	public ResponseEntity<?> handledExceptions(RuntimeException ex) {
//		log.error(" ERROR , ERROR , ERROR " , ex);
//		return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public Map<String,String> handledBadRequestExceptions(MethodArgumentNotValidException ex) {
		log.error(":::::: BAD REQUEST :::  " , ex);
		Map<String,String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String filedName = ((FieldError) error ).getField();
			String message = error.getDefaultMessage();
			errors.put(filedName, message);
		});
		return errors;
	}
}

