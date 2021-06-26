package com.api1.service;

import com.api1.model.Product;

public interface ProductService {
	
	public Product getProductById(String productId);
	public Product addProduct(Product product);
	public Product updateProduct(Product product);
	public String deleteProduct(String productId);

}
