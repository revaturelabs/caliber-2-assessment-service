package com.revature.caliber.controllers;

import static com.revature.caliber.services.ErrorConstants.CATEGORY_CANNOT_BE_NULL;
import static com.revature.caliber.services.ErrorConstants.CATEGORY_DOES_NOT_EXIST;
import static com.revature.caliber.services.ErrorConstants.OWNER_CANNOT_BE_NULL;
import static com.revature.caliber.services.ErrorConstants.SKILL_TYPE_ALREADY_EXISTS;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revature.caliber.exceptions.CategoryNullException;
import com.revature.caliber.exceptions.DoesNotExistException;
import com.revature.caliber.exceptions.DuplicateException;
import com.revature.caliber.exceptions.ErrorMessage;
import com.revature.caliber.exceptions.OwnerNullException;

@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<?> handleException(DuplicateException e) {
		ErrorMessage errorMessage = new ErrorMessage(SKILL_TYPE_ALREADY_EXISTS);
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CategoryNullException.class)
	public ResponseEntity<?> handleException(CategoryNullException e) {
		ErrorMessage errorMessage = new ErrorMessage(CATEGORY_CANNOT_BE_NULL);
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DoesNotExistException.class)
	public ResponseEntity<?> handleException(DoesNotExistException e) {
		ErrorMessage errorMessage = new ErrorMessage(CATEGORY_DOES_NOT_EXIST);
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(OwnerNullException.class)
	public ResponseEntity<?> handleException(OwnerNullException e) {
		ErrorMessage errorMessage = new ErrorMessage(OWNER_CANNOT_BE_NULL);
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
