package com.galaxy.trader;

/**
 * Exception class for representing processing errors
 * 
 * @author Abhi
 * 
 */
public class InputProcessingException extends Exception {

	public InputProcessingException() {
	}

	public InputProcessingException(String message) {
		super(message);
	}

	public InputProcessingException(Throwable cause) {
		super(cause);
	}

	public InputProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

}
