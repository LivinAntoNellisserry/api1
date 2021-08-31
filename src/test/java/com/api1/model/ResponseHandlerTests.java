package com.api1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResponseHandlerTests {

	ResponseHandler responseHandler;
	ProductClone productClone;
	String today = Date.valueOf(LocalDate.now()).toString();

	@BeforeEach
	public void setup() {
		productClone = new ProductClone();
		productClone.setCloneId(1);
		productClone.setCloneProductId("A1");
		productClone.setCloneProductName("Rice");
		productClone.setCloneProductExpiryDate(today);

		responseHandler = new ResponseHandler();
		responseHandler.setProductClone(productClone);
		responseHandler.setResponseMessage("PRODUCT SAVED");
		responseHandler.setResponseType("SUCCESS");

	}

	@Test
	public void getProductResponse() {
		assertEquals(productClone, responseHandler.getProductClone(), "getProductClone not implemented properly");
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
				"ResponseHandler [responseType=SUCCESS, responseMessage=PRODUCT SAVED, productClone=ProductClone [cloneId=1, cloneProductId=A1, cloneProductName=Rice, cloneProductExpiryDate="
						+ today + "]]",
				responseHandler.toString(), "toString not implemented properly");
	}

}
