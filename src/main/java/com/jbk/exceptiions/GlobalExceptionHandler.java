package com.jbk.exceptiions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> xyz(MethodArgumentNotValidException ex) {

		Map<String, String> map = new HashMap<>();

		ex.getFieldErrors().forEach(fieldError -> map.put(fieldError.getField(), fieldError.getDefaultMessage()));

//		List<FieldError> fieldErrors = ex.getFieldErrors();
//
//		for (FieldError fieldError : fieldErrors) {
//
//			String fieldName = fieldError.getField();
//			String defaultMessage = fieldError.getDefaultMessage();
//
//			map.put(fieldName, defaultMessage);
//
//		}

		return map;

	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public String resourceAlreadyExistException(ResourceAlreadyExistException ex) {
		return ex.getMessage();
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.OK)
	public String resourceNotFoundException(ResourceNotFoundException ex) {
		return ex.getMessage();
		
	}

}
