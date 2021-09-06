package com.api1.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;
import com.api1.model.ProductClone;
import com.api1.model.Response;
import com.api1.model.ResponseHandler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {

	@Autowired
	ProductService productService;
	
	@MockBean
	WebClient.Builder webClientBuilder;
	
	Product product;
	Response response;
	ProductClone productClone;
	ResponseHandler responseHandler;
	
	
	@Value("${get_product_by_id.url}")
	private String GET_PRODUCT_BY_ID_URI;

	@Value("${post_add_product.url}")
	private String POST_ADD_PRODUCT_URI;

	@Value("${post_update_product.url}")
	private String POST_UPDATE_PRODUCT_URI;

	@Value("${get_delete_product.url}")
	private String GET_DELETE_PRODUCT_URI;
	
	
	@BeforeEach
	public void setup()
	{
		product = new Product();
		product.setId(1);
		product.setProductExpiryDate(Date.valueOf(LocalDate.now()).toString());
		product.setProductId("A1");
		product.setProductName("Burger");
		
		response = new Response();
		response.setProduct(product);
		response.setStatus("NOT EXPIRED");
		
		responseHandler = new ResponseHandler();
		responseHandler.setProductClone(this.ProductToProductClone(product));
		responseHandler.setResponseMessage("NOT EXPIRED");
		responseHandler.setResponseType("SUCCESS");
	}
	
	@AfterEach
	public void tearDown()
	{
		product = null;
		response = null;
		responseHandler = null;
	}
	
	@Test
	public void getProductById() throws Exception
	{
		
		
	}
	
	private Product ProductCloneToProduct(ProductClone productClone) {
		Product product = new Product();
		product.setId(productClone.getCloneId());
		product.setProductId(productClone.getCloneProductId());
		product.setProductName(productClone.getCloneProductName());
		product.setProductExpiryDate(productClone.getCloneProductExpiryDate().toString());
		return product;
	}

	private ProductClone ProductToProductClone(Product product) {
		ProductClone productClone = new ProductClone();
		productClone.setCloneId(product.getId());
		productClone.setCloneProductId(product.getProductId());
		productClone.setCloneProductName(product.getProductName());
		productClone.setCloneProductExpiryDate(product.getProductExpiryDate());
		return productClone;
	}
}
