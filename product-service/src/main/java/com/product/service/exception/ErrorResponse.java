package com.product.service.exception;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ErrorResponse {

	private LocalDateTime timestamp;
	private int status;
	private String errorCode;
	private String message;
	private String path;
	private String traceId;
	
	private Map<String,String> errors;

}
