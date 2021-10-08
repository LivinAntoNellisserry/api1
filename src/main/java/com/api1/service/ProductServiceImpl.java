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
import com.api1.model.ProductClone;
import com.api1.model.Api2Response;
import com.api1.model.Api1Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	private static final String FAILED = "FAILED";
	private static final String API2CALL = "Called Api2";
	private static final String NULLRETURN = "Api2Service returned a null";
	private static final String UNEXPECTEDERROR = "Unexpected error, Api2Service cannot return null";
	
	@Value("${get_product_by_id.url}")
	private String GET_PRODUCT_BY_ID_URI;

	@Value("${post_add_product.url}")
	private String POST_ADD_PRODUCT_URI;

	@Value("${post_update_product.url}")
	private String POST_UPDATE_PRODUCT_URI;

	@Value("${get_delete_product.url}")
	private String GET_DELETE_PRODUCT_URI;

	@Autowired
	WebClient webClient;

	public Api1Response getProductById(String productId) throws ProductNotFoundException {
		log.info("Called getProductById Service");
		log.debug(productId);
		log.info(API2CALL);
		log.debug(GET_PRODUCT_BY_ID_URI);
		Api2Response api2Response = webClient.get().uri(GET_PRODUCT_BY_ID_URI, productId).retrieve()
				.bodyToMono(Api2Response.class).block();
		if (api2Response == null) {
			log.warn(NULLRETURN);
			log.error(UNEXPECTEDERROR);
			return null;
		}

		if (api2Response.getResponseType().equals(FAILED)) {
			log.info("Couldnt find product");
			log.debug(productId);
			log.info("Throwed ProductNotFoundException");
			throw new ProductNotFoundException(api2Response.getResponseMessage());
		}
		log.info("Product was found");
		if (log.isDebugEnabled()) {
			log.debug(api2Response.toString());
		}
		log.info("Exited getProductById Service");
		return this.getResponse(api2Response);

	}

	public Api1Response addProduct(Product product) throws ProductAlreadyPresentException {

		log.info("Called addProduct Service");
		if (log.isDebugEnabled()) {
			log.debug(product.toString());
		}
		log.info(API2CALL);
		log.debug(POST_ADD_PRODUCT_URI);
		Api2Response api2Response = webClient.post().uri(POST_ADD_PRODUCT_URI)
				.bodyValue(this.ProductToProductClone(product)).retrieve().bodyToMono(Api2Response.class).block();
		if (api2Response == null) {
			log.warn(NULLRETURN);
			log.error(UNEXPECTEDERROR);
			return null;
		}
		if (api2Response.getResponseType().equals(FAILED)) {
			log.info("Couldnt add product");
			if (log.isDebugEnabled()) {
				log.debug(product.toString());
			}
			log.info("Throwed ProductAlreadyPresentException");
			throw new ProductAlreadyPresentException(api2Response.getResponseMessage());
		}
		log.info("Product was Added");
		if (log.isDebugEnabled()) {
			log.debug(api2Response.toString());
		}
		log.info("Exited addProduct Service");
		return this.getResponse(api2Response);
	}

	public Api1Response updateProduct(Product product) throws ProductNotFoundException {
		log.info("Called updateProduct Service");
		if (log.isDebugEnabled()) {
			log.debug(product.toString());
		}
		log.info(API2CALL);

		log.debug(POST_UPDATE_PRODUCT_URI);
		Api2Response api2Response = webClient.post().uri(POST_UPDATE_PRODUCT_URI)
				.bodyValue(this.ProductToProductClone(product)).retrieve().bodyToMono(Api2Response.class).block();
		if (api2Response == null) {
			log.warn(NULLRETURN);
			log.error(UNEXPECTEDERROR);
			return null;
		}
		if (api2Response.getResponseType().equals(FAILED)) {
			log.info("Couldnt update product");
			if (log.isDebugEnabled()) {
				log.debug(product.toString());
			}
			log.info("Throwed ProductNotFoundException");
			throw new ProductNotFoundException(api2Response.getResponseMessage());
		}
		log.info("Product was Updated");
		if (log.isDebugEnabled()) {
			log.debug(api2Response.toString());
		}
		log.info("Exited updateProduct Service");
		return this.getResponse(api2Response);
	}

	public String deleteProduct(String productId) throws ProductNotDeletedException {
		log.info("Called deleteProduct Service");
		log.debug(productId);
		log.info(API2CALL);
		log.debug(GET_DELETE_PRODUCT_URI);
		Api2Response api2Response = webClient.get().uri(GET_DELETE_PRODUCT_URI, productId).retrieve()
				.bodyToMono(Api2Response.class).block();
		if (api2Response == null) {
			log.warn(NULLRETURN);
			log.error(UNEXPECTEDERROR);
			return null;
		}
		if (api2Response.getResponseType().equals(FAILED)) {
			log.info("Couldnt delete product");
			log.debug(productId);
			log.info("Throwed ProductNotDeletedException");
			throw new ProductNotDeletedException(api2Response.getResponseMessage());
		}
		log.info("Product was deleted");
		if (log.isDebugEnabled()) {
			log.debug(api2Response.toString());
		}
		log.info("Exited deleteProduct Service");
		return api2Response.getResponseMessage();
	}

	/**
	 * Returns api1response created from api2response.
	 * 
	 * @param responseHandler
	 * @return response
	 */
	private Api1Response getResponse(Api2Response api2Response) {
		Api1Response api1Response = new Api1Response();
		Product product = this.ProductCloneToProduct((api2Response.getProductClone()));
		api1Response.setProduct(product);
		api1Response.setStatus(api2Response.getResponseMessage());
		return api1Response;
	}

	/**
	 * Returns a product created from productClone.
	 * 
	 * @param productClone
	 * @return product
	 */
	private Product ProductCloneToProduct(ProductClone productClone) {
		Product product = new Product();
		product.setId(productClone.getCloneId());
		product.setProductId(productClone.getCloneProductId());
		product.setProductName(productClone.getCloneProductName());
		product.setProductExpiryDate(productClone.getCloneProductExpiryDate().toString());
		return product;
	}

	/**
	 * Return productClone created from product.
	 * 
	 * @param product
	 * @return productClone
	 */
	private ProductClone ProductToProductClone(Product product) {
		ProductClone productClone = new ProductClone();
		productClone.setCloneId(product.getId());
		productClone.setCloneProductId(product.getProductId());
		productClone.setCloneProductName(product.getProductName());
		productClone.setCloneProductExpiryDate(product.getProductExpiryDate());
		return productClone;
	}

}
