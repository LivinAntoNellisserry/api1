package com.api1.service;

import com.api1.exception.ProductAlreadyPresentException;
import com.api1.exception.ProductNotDeletedException;
import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;
import com.api1.model.View;

public interface ProductService {
	/**
	 * Method to get Product by Id
	 * 
	 * @param productId input the productId
	 * @return view returns a view of product with status
	 * @throws ProductNotFoundException
	 */
	public View getProductById(String productId) throws ProductNotFoundException;

	/**
	 * Method to Add Product
	 * 
	 * @param product input the product
	 * @return product returns the stored product
	 * @throws ProductAlreadyPresentException
	 */
	public Product addProduct(Product product) throws ProductAlreadyPresentException;

	/**
	 * Method to update Product
	 * 
	 * @param product input the product
	 * @return product returns the updated product
	 * @throws ProductNotFoundException
	 */
	public Product updateProduct(Product product) throws ProductNotFoundException;

	/**
	 * Method to delete the product by Id
	 * 
	 * @param productId input the product Id
	 * @return string returns the status whether the product is deleted
	 * @throws ProductNotDeletedException
	 */
	public String deleteProduct(String productId) throws ProductNotDeletedException;

}
