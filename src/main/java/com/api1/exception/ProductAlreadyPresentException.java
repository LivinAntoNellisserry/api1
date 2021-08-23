package com.api1.exception;

/**
 * 
 * ProductAlreadyPresentException Class.
 *
 */
public class ProductAlreadyPresentException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductAlreadyPresentException(String message) {
		super(message);
	}
}
