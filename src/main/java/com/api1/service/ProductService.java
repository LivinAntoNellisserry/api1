package com.api1.service;

import com.api1.exceptions.ProductExpiryDateException;
import com.api1.exceptions.ProductIdException;
import com.api1.exceptions.ProductNameException;
import com.api1.model.Product;

public interface ProductService {
	
	public Product getProductById(String productId) throws ProductIdException;
	public Product addProduct(Product product) throws ProductIdException, ProductNameException, ProductExpiryDateException;

}
