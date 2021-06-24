package com.api1.exceptions;

public class ProductExpiryDateException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProductExpiryDateException(String message) {
		super(message);
	}

}
