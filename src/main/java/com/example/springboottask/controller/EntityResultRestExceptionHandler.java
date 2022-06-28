package com.example.springboottask.controller;

import com.example.springboottask.service.ApplicationProperties;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@Setter
//@ConfigurationProperties("property")
public class EntityResultRestExceptionHandler {

//	@Value("${PROPERTY_DEV_MESSAGE}")
	@Autowired
	ApplicationProperties applicationProperties;

	@ExceptionHandler(EntityResultNotFoundException.class)
	public ResponseEntity<EntityResultErrorResponse> handleException(EntityResultErrorResponse exc) {
		EntityResultErrorResponse error = new EntityResultErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
				System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<EntityResultErrorResponse> handleException(Exception exc) {
		EntityResultErrorResponse error = new EntityResultErrorResponse(HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(), System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<Field> details = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(new Field(error.getObjectName(), error.getDefaultMessage()));
		}
		var message = "Validation error";
		var devMessageResponse = Objects.equals(applicationProperties.getDevMessage(), "true")
				? ex.getMessage()
				: null;

		var error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, devMessageResponse, LocalDateTime.now(),
				getCurrentRequestPath(), details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	private String getCurrentRequestPath() {
		return Objects.toString(ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath());
	}
}