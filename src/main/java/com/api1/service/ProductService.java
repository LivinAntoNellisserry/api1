package com.api1.service;

import com.api1.exception.ProductAlreadyPresentException;
import com.api1.exception.ProductNotDeletedException;
import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;

public interface ProductService {
	
	public Product getProductById(String productId) throws ProductNotFoundException;
	public Product addProduct(Product product) throws ProductAlreadyPresentException;
	public Product updateProduct(Product product) throws ProductNotFoundException;
	public String deleteProduct(String productId) throws ProductNotDeletedException;

}
