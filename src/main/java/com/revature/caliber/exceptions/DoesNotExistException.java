package com.revature.caliber.exceptions;

public class DoesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoesNotExistException(String message) {
		super(message);
	}
}
