package com.api1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.api1.exception.ProductAlreadyPresentException;
import com.api1.exception.ProductNotDeletedException;
import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;
import com.api1.model.ResponseHandler;

@Service
public class ProductServiceImpl implements ProductService {

	private final String GET_PRODUCT_BY_ID_URI = "http://localhost:8082/api2/search/{productId}";
	private final String POST_ADD_PRODUCT_URI = "http://localhost:8082/api2/add";
	private final String POST_UPDATE_PRODUCT_URI = "http://localhost:8082/api2/update";
	private final String GET_DELETE_PRODUCT_URI = "http://localhost:8082/api2/delete/{productId}";

	@Autowired
	WebClient.Builder webClientBuilder;

	public Product getProductById(String productId) throws ProductNotFoundException {

		ResponseHandler response = webClientBuilder.build().get().uri(GET_PRODUCT_BY_ID_URI, productId).retrieve()
				.bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			throw new ProductNotFoundException(response.getResponseMessage());
		}
		return response.getResponseProduct();

	}

	public Product addProduct(Product product) throws ProductAlreadyPresentException {

		ResponseHandler response = webClientBuilder.build().post().uri(POST_ADD_PRODUCT_URI).bodyValue(product)
				.retrieve().bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			throw new ProductAlreadyPresentException(response.getResponseMessage());
		}
		return response.getResponseProduct();

	}

	public Product updateProduct(Product product) throws ProductNotFoundException {
		ResponseHandler response = webClientBuilder.build().post().uri(POST_UPDATE_PRODUCT_URI).bodyValue(product)
				.retrieve().bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			throw new ProductNotFoundException(response.getResponseMessage());
		}
		return response.getResponseProduct();
	}

	public String deleteProduct(String productId) throws ProductNotDeletedException {
		ResponseHandler response = webClientBuilder.build().get().uri(GET_DELETE_PRODUCT_URI, productId).retrieve()
				.bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			throw new ProductNotDeletedException(response.getResponseMessage());
		}
		return response.getResponseMessage();

	}

}
