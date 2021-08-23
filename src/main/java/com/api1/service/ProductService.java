package com.api1.service;

import com.api1.exception.ProductAlreadyPresentException;
import com.api1.exception.ProductNotDeletedException;
import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;
import com.api1.model.Response;

public interface ProductService {
	/**
	 * Returns a response containing the product and the status.
	 * 
	 * @param productId
	 * @return response
	 * @throws ProductNotFoundException
	 */
	public Response getProductById(String productId) throws ProductNotFoundException;

	/**
	 * Saves the product and returns the response containing product and the status.
	 * 
	 * @param product
	 * @return response
	 * @throws ProductAlreadyPresentException
	 */
	public Response addProduct(Product product) throws ProductAlreadyPresentException;

	/**
	 * Updates the product and returns the response containing product and the
	 * status.
	 * 
	 * @param product
	 * @return response
	 * @throws ProductNotFoundException
	 */
	public Response updateProduct(Product product) throws ProductNotFoundException;

	/**
	 * Deletes the product and returns the message.
	 * 
	 * @param productId
	 * @return string
	 * @throws ProductNotDeletedException
	 */
	public String deleteProduct(String productId) throws ProductNotDeletedException;

}
