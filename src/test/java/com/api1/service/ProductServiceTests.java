package com.api1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;
import com.api1.model.ProductClone;
import com.api1.model.Api1Response;
import com.api1.model.Api2Response;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

	ProductService productService;

	@Value("${get_product_by_id.url}")
	private String GET_PRODUCT_BY_ID_URI;

	@Value("${post_add_product.url}")
	private String POST_ADD_PRODUCT_URI;

	@Value("${post_update_product.url}")
	private String POST_UPDATE_PRODUCT_URI;

	@Value("${get_delete_product.url}")
	private String GET_DELETE_PRODUCT_URI;

	@Mock
	private WebClient.Builder webClientBuilder;


	@Test
	public void getProductById() throws Exception {

		String productId = "A1";
		Product product = new Product();
		product.setId(1);
		product.setProductExpiryDate(Date.valueOf(LocalDate.now()).toString());
		product.setProductId("A1");
		product.setProductName("Burger");

		Api1Response response = new Api1Response();
		response.setProduct(product);
		response.setStatus("NOT EXPIRED");

		Api2Response responseHandler = new Api2Response();
		responseHandler.setProductClone(this.ProductToProductClone(product));
		responseHandler.setResponseMessage("NOT EXPIRED");
		responseHandler.setResponseType("SUCCESS");


		when(webClientBuilder.build().get().uri(GET_PRODUCT_BY_ID_URI, productId)
				.retrieve().bodyToMono(Api2Response.class).block()).thenReturn(responseHandler);

		assertEquals(response,productService.getProductById(productId));
		
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