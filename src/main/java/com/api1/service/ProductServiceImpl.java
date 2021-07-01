package com.api1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

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
		log.info("Called getProductById service");

		ResponseHandler response = webClientBuilder.build().get().uri(GET_PRODUCT_BY_ID_URI, productId).retrieve()
				.bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			log.info("Throwed Product Not Found Exception");
			throw new ProductNotFoundException(response.getResponseMessage());
		}
		log.info("Product was found");
		log.info("Exited getProductById service");
		return this.getView(response);

	}

	public Product addProduct(Product product) throws ProductAlreadyPresentException {

		log.info("Called addProduct Service");
		ResponseHandler response = webClientBuilder.build().post().uri(POST_ADD_PRODUCT_URI).bodyValue(product)
				.retrieve().bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			log.info("Throwed Product Already Present Exception");
			throw new ProductAlreadyPresentException(response.getResponseMessage());
		}
		log.info("Product was Added");
		log.info("Exited addProduct service");
		return response.getProductResponse();
	}

	public Product updateProduct(Product product) throws ProductNotFoundException {
		log.info("Called updateProductService");
		ResponseHandler response = webClientBuilder.build().post().uri(POST_UPDATE_PRODUCT_URI).bodyValue(product)
				.retrieve().bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			log.info("Throwed Product Not Found Exception");
			throw new ProductNotFoundException(response.getResponseMessage());
		}
		log.info("Product was Updated");
		log.info("Exited updateProduct service");
		return response.getProductResponse();
	}

	public String deleteProduct(String productId) throws ProductNotDeletedException {
		log.info("Called deleteProduct service");
		ResponseHandler response = webClientBuilder.build().get().uri(GET_DELETE_PRODUCT_URI, productId).retrieve()
				.bodyToMono(ResponseHandler.class).block();
		if (response.getResponseType().equals("FAILED")) {
			log.info("Throwed Product Not Deleted Exception");
			throw new ProductNotDeletedException(response.getResponseMessage());
		}
		log.info("Product is Deleted");
		log.info("Exited deleteProduct service");
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
