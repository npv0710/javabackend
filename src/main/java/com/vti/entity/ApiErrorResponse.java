package com.vti.entity;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NonNull;

@Data
public class ApiErrorResponse {
	
	@NonNull
	private HttpStatus status;

	@NonNull
	private String message;
	
	@NonNull
	private String detailMessage;
	
}
