package com.api1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.api1.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private final String GET_PRODUCT_BY_ID_URI = "http://localhost:8082/api2/search/{productId}";
	private final String POST_ADD_PRODUCT_URI = "http://localhost:8082/api2/add";
	private final String POST_UPDATE_PRODUCT_URI = "http://localhost:8082/api2/update";
	private final String GET_DELETE_PRODUCT_URI = "http://localhost:8082/api2/delete/{productId}";

	@Autowired
	WebClient.Builder webClientBuilder;

	public Product getProductById(String productId) {

		return webClientBuilder.build().get().uri(GET_PRODUCT_BY_ID_URI, productId).retrieve().bodyToMono(Product.class)
				.block();

	}

	public Product addProduct(Product product) {

		return webClientBuilder.build().post().uri(POST_ADD_PRODUCT_URI).bodyValue(product).retrieve()
				.bodyToMono(Product.class).block();
	}

	public Product updateProduct(Product product) {
		return webClientBuilder.build().post().uri(POST_UPDATE_PRODUCT_URI).bodyValue(product).retrieve()
				.bodyToMono(Product.class).block();
	}

	public String deleteProduct(String productId) {
		return webClientBuilder.build().get().uri(GET_DELETE_PRODUCT_URI, productId).retrieve()
				.bodyToMono(String.class).block();
		
	}

}
