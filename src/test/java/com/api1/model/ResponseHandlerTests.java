package com.api1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResponseHandlerTests {

	ResponseHandler responseHandler;
	Product product;
	String today = Date.valueOf(LocalDate.now()).toString();

	@BeforeEach
	public void setup() {
		product = new Product();
		product.setId(1);
		product.setProductId("A1");
		product.setProductName("Rice");
		product.setProductExpiryDate(today);

		responseHandler = new ResponseHandler();
		responseHandler.setProductResponse(product);
		responseHandler.setResponseMessage("PRODUCT SAVED");
		responseHandler.setResponseType("SUCCESS");

	}

	@Test
	public void getProductResponse() {
		assertEquals(product, responseHandler.getProductResponse(), "getProductResponse not implemented properly");
	}

	@Test
	public void getResponseMessage() {
		assertEquals("PRODUCT SAVED", responseHandler.getResponseMessage(),
				"getResponseMessage not implemented properly");
	}

	@Test
	public void getResponseType() {
		assertEquals("SUCCESS", responseHandler.getResponseType(), "getResponseType not implemented properly");
	}

	@Test
	public void toStringTest() {
		assertEquals(
				"ResponseHandler [responseType=SUCCESS, responseMessage=PRODUCT SAVED, productResponse=Product [id=1, productId=A1, productName=Rice, productExpiryDate="
						+ today + "]]",
				responseHandler.toString(), "toString not implemented properly");
	}

}
