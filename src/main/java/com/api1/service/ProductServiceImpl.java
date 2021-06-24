package com.api1.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.api1.exceptions.ProductExpiryDateException;
import com.api1.exceptions.ProductIdException;
import com.api1.exceptions.ProductNameException;
import com.api1.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final String GET_PRODUCT_BY_ID_URI = "http://localhost:8082/api2/search/{productId}";
	private final String POST_ADD_PRODUCT = "http://localhost:8082/api2/add";
	
	@Autowired
	WebClient.Builder webClientBuilder;

	public Product getProductById(String productId) throws ProductIdException {
		if (!((productId.length() <= 10) && (productId.length() > 0))) {
			throw new ProductIdException("Product ID should be present and maximum lenght be 10");
		}
		
		return webClientBuilder.build()
				.get()
				.uri(GET_PRODUCT_BY_ID_URI, productId)
				.retrieve()
				.bodyToMono(Product.class)
				.block();

	}

	public Product addProduct(Product product)
			throws ProductIdException, ProductNameException, ProductExpiryDateException {
		if (!((product.getProductId().length() <= 10) && (product.getProductId().length() > 0))) {
			throw new ProductIdException("Product ID should be present and maximum lenght be 10");
		}
		if (!((product.getProductName().length() <= 10) && (product.getProductName().length() > 0))) {
			throw new ProductNameException("Product Name should be present and maximum lenght be 10");
		}
		if (product.getProductExpiryDate().before(Date.valueOf(LocalDate.now()))) {
			throw new ProductExpiryDateException("This item is expired");
		}
		if(product.getProductExpiryDate().equals(null)) {
			throw new ProductExpiryDateException("Please enter a date");
		}
		return webClientBuilder.build()
				.post()
				.uri(POST_ADD_PRODUCT)
				.bodyValue(product)
				.retrieve()
				.bodyToMono(Product.class)
				.block();
	}

}
