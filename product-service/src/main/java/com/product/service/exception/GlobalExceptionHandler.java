package com.product.service.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex,
			HttpServletRequest request) {

		ErrorResponse error = new ErrorResponse();

		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		error.setErrorCode("PRODUCT_NOT_FOUND");
		error.setTraceId(null);

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		Map<String, String> errors = new HashMap<>();
		BindingResult bindingresult = ex.getBindingResult();

		for (FieldError fielderror : bindingresult.getFieldErrors()) {
			errors.put(fielderror.getField(), fielderror.getDefaultMessage());
		}

		ErrorResponse error = new ErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Validation Failed ");
		error.setPath(request.getRequestURI());
		error.setErrorCode("VALIDATION_ERROR");
		error.setTraceId(null);
		error.setErrors(errors);

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

	}

}
