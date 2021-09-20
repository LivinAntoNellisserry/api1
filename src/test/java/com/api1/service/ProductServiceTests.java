package com.api1.service;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.api1.model.Api1Response;
import com.api1.model.Api2Response;
import com.api1.model.Product;
import com.api1.model.ProductClone;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {

	@Autowired
	ProductServiceImpl service;

	public static MockWebServer mockBackEnd;

	@BeforeAll
	static void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	Api2Response api2Response;
	Product product;
	ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		api2Response = new Api2Response();
		product = new Product();
	}

	@Test
	public void getProductById() throws Exception {

		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate("2021-12-12");

		api2Response.setProductClone(this.getProductClone(product));
		api2Response.setResponseMessage("NOT EXPIRED");
		api2Response.setResponseType("SUCCESS");

		mockBackEnd.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(api2Response))
				.addHeader("Content-Type", "application/json"));

		Api1Response actualApi1Response = service.getProductById("G1");
		System.out.println(actualApi1Response);

	}

	private ProductClone getProductClone(Product product) {
		ProductClone productClone = new ProductClone();
		productClone.setCloneId(product.getId());
		productClone.setCloneProductExpiryDate(product.getProductExpiryDate().toString());
		productClone.setCloneProductId(product.getProductId());
		productClone.setCloneProductName(product.getProductName());
		return productClone;
	}

}
