package com.gorillaz.core.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gorillaz.core.exceptions.db.DBExceptions;


@EnableWebMvc
@RestControllerAdvice
public class ControllerExceptions {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ErrorResponse handledBadRequestExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String message = error.getDefaultMessage();
			errors.put("message", message);
		});
		return new ErrorResponse(errors.get("message"),400, null);
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler({DBExceptions.class})
	public ErrorResponse handledDBExceptions(DBExceptions ex) {
		return new ErrorResponse(ex.getMessage(), 404, null);
	}
	
}
