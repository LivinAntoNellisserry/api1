package com.api1.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

class ProductTests {

	@Spy
	Product product;
	
	String today = Date.valueOf(LocalDate.now()).toString();

	@BeforeEach
	public void setup() {
		product = new Product();
		product.setId(1);
		product.setProductId("A1");
		product.setProductName("Rice");
		product.setProductExpiryDate(today);
	}

	@Test
	public void getId() {

		assertEquals(1, product.getId(), "getId not implemented properly");
	}

	@Test
	public void getProductId() {
		assertEquals("A1", product.getProductId(), "getProductId not implemented properly");
	}

	@Test
	public void getProductName() {
		assertEquals("Rice", product.getProductName(), "getProductName not implemented properly");
	}

	@Test
	public void getProductExpiryDate() {
		assertEquals(today, product.getProductExpiryDate(), "getProductExpiryDate not implemented properly");
	}

	@Test
	public void toStringTest() {
		assertEquals("Product [id=1, productId=A1, productName=Rice, productExpiryDate=" + today + "]",
				product.toString(), "toString not implemented properly");
	}

}
