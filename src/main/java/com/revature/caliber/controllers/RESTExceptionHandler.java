package com.revature.caliber.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revature.caliber.exceptions.DoesNotExistException;
import com.revature.caliber.exceptions.DuplicateException;
import com.revature.caliber.exceptions.ErrorMessage;

import static com.revature.caliber.services.ErrorConstants.*;

@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<?> handleException(DuplicateException e) {
		ErrorMessage errorMessage = new ErrorMessage(DUPLICATE_ERROR);
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DoesNotExistException.class)
	public ResponseEntity<?> handleException(DoesNotExistException e){
		ErrorMessage errorMessage = new ErrorMessage(DOESNOTEXIST_ERROR);
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
