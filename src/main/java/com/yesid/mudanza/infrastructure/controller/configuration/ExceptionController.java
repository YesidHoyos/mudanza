package com.yesid.mudanza.infrastructure.controller.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yesid.mudanza.application.exception.UnexpectedException;
import com.yesid.mudanza.domain.exception.OutOfRangeException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = {OutOfRangeException.class})
	public ResponseEntity<Object> conflicto(OutOfRangeException excepcion, WebRequest request) {
		String bodyOfResponse = excepcion.getMessage();
        return handleExceptionInternal(excepcion, bodyOfResponse, new HttpHeaders(), HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, request);		
	}
	
	@ExceptionHandler(UnexpectedException.class)
	public ResponseEntity<Object> noExiste(UnexpectedException excepcion, WebRequest request) {
		String bodyOfResponse = excepcion.getMessage();
        return handleExceptionInternal(excepcion, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);		
	}
}
