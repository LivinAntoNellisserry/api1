package com.api1.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.api1.model.Api1Response;
import com.api1.model.Api2Response;
import com.api1.model.Product;
import com.api1.model.ProductClone;
import com.google.gson.Gson;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class ProductServiceMockWebServerTests {

	public static MockWebServer mockBackEnd;
	
	@Autowired
	ProductServiceImpl productServiceImpl;

	@BeforeAll
	static void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@BeforeEach
	void initialize() {
		String baseUrl = String.format("http://api-2", mockBackEnd.getPort(),"/api2/search/");
		productServiceImpl = new ProductServiceImpl();
	}

	@Test
	public void getProductById() throws Exception {
		String productId = "A1";
		Product product = new Product();
		product.setId(1);
		product.setProductExpiryDate(Date.valueOf(LocalDate.now()).toString());
		product.setProductId("A1");
		product.setProductName("Burger");

		Api2Response api2Response = new Api2Response();
		api2Response.setProductClone(this.ProductToProductClone(product));
		api2Response.setResponseMessage("NOT EXPIRED");
		api2Response.setResponseType("SUCCESS");

		Api1Response api1Response = new Api1Response();
		api1Response.setProduct(product);
		api1Response.setStatus("NOT EXPIRED");

		System.out.println(new Gson().toJson(api2Response));
		mockBackEnd.enqueue(new MockResponse().setBody(new Gson().toJson(api2Response)).addHeader("Content-Type",
				"application/json"));

		Api1Response response = productServiceImpl.getProductById(productId);
		assertEquals(api1Response, response, "Problems in Get MEthod");

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
