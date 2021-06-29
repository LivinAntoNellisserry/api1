package com.api1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.api1.exception.ProductAlreadyPresentException;
import com.api1.exception.ProductNotDeletedException;
import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;
import com.api1.model.ResponseHandler;
import com.api1.model.View;

@Service
public class ProductServiceImpl implements ProductService {

	@Value("${get_product_by_id.url}")
	private String GET_PRODUCT_BY_ID_URI;

	@Value("${post_add_product.url}")
	private String POST_ADD_PRODUCT_URI;

	@Value("${post_update_product.url}")
	private String POST_UPDATE_PRODUCT_URI;

	@Value("${get_delete_product.url}")
	private String GET_DELETE_PRODUCT_URI;

	@Autowired
	WebClient.Builder webClientBuilder;

	public View getProductById(String productId) throws ProductNotFoundException {

		ResponseHandler response = webClientBuilder.build().get().uri(GET_PRODUCT_BY_ID_URI, productId).retrieve()
				.bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			throw new ProductNotFoundException(response.getResponseMessage());
		}
		return this.getView(response);

	}

	public Product addProduct(Product product) throws ProductAlreadyPresentException {

		ResponseHandler response = webClientBuilder.build().post().uri(POST_ADD_PRODUCT_URI).bodyValue(product)
				.retrieve().bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			throw new ProductAlreadyPresentException(response.getResponseMessage());
		}
		return response.getProductResponse();
	}

	public Product updateProduct(Product product) throws ProductNotFoundException {
		ResponseHandler response = webClientBuilder.build().post().uri(POST_UPDATE_PRODUCT_URI).bodyValue(product)
				.retrieve().bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			throw new ProductNotFoundException(response.getResponseMessage());
		}
		return response.getProductResponse();
	}

	public String deleteProduct(String productId) throws ProductNotDeletedException {
		ResponseHandler response = webClientBuilder.build().get().uri(GET_DELETE_PRODUCT_URI, productId).retrieve()
				.bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			throw new ProductNotDeletedException(response.getResponseMessage());
		}
		return response.getResponseMessage();

	}

	/**
	 * Method to get View for user from response with the status
	 * 
	 * @param response input the response
	 * @return view returns the view
	 */
	private View getView(ResponseHandler response) {
		View view = new View();
		Product product = response.getProductResponse();
		view.setProduct(product);
		view.setStatus(response.getResponseMessage());
		return view;
	}

}
