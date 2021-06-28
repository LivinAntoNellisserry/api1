package com.api1.exception;

public class ProductNotDeletedException extends Exception {

private static final long serialVersionUID = 1L;
	
	public ProductNotDeletedException(String message)
	{
		super(message);
	}
}
