package com.api1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResponseTests {

	Response response;
	Product product;
	String today = Date.valueOf(LocalDate.now()).toString();

	@BeforeEach
	public void setup() {
		product = new Product();
		product.setId(1);
		product.setProductId("A1");
		product.setProductName("Rice");
		product.setProductExpiryDate(today);

		response = new Response();
		response.setProduct(product);
		response.setStatus("SUCCESS");
	}

	@Test
	public void getProduct() {
		assertEquals(product, response.getProduct(), "getProduct not implemented properly");
	}

	@Test
	public void getStatus() {
		assertEquals("SUCCESS", response.getStatus(), "getStatus not implemented properly");
	}

	@Test
	public void toStringTest() {
		assertEquals("Response [product=Product [id=1, productId=A1, productName=Rice, productExpiryDate=" + today
				+ "], status=SUCCESS]", response.toString(), "toString is not implemented properly");
	}

}
