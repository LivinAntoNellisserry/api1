package com.api1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.api1.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private final String GET_PRODUCT_BY_ID_URI = "http://localhost:8082/api2/search/{productId}";
	private final String POST_ADD_PRODUCT = "http://localhost:8082/api2/add";

	@Autowired
	WebClient.Builder webClientBuilder;

	public Product getProductById(String productId) {

		return webClientBuilder.build().get().uri(GET_PRODUCT_BY_ID_URI, productId).retrieve().bodyToMono(Product.class)
				.block();

	}

	public Product addProduct(Product product) {

		return webClientBuilder.build().post().uri(POST_ADD_PRODUCT).bodyValue(product).retrieve()
				.bodyToMono(Product.class).block();
	}

}
