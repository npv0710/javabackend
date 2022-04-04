package com.vti.configuration;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vti.exception.ErrorResponse;
import com.vti.exception.NotFoundException;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	// not found entity (getByID not found)
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException exception) {
		return ResponseEntity.status(432).body("Entity not found exception");
	}
	
	@ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundException(NotFoundException ex, WebRequest req) {
        // Log err

        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }
	
	@ExceptionHandler
    public ResponseEntity<?> handleAccessDeniedException(NotFoundException e){

        

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("123abc not found");
    }
	 
	

}
