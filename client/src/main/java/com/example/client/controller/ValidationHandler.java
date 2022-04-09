package com.example.client.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> dataStartAfterDataEnsException(Exception exp) {
		exp=new Exception("Bad Request");
		return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
