package com.api1.config;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 * Api1ControllerAdvisor Class.
 *
 */
@RestControllerAdvice
public class Api1ControllerAdvisor {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> validationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		String error = "";
		for (FieldError fieldError : fieldErrors) {
			error = fieldError.getDefaultMessage();
		}
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

}
