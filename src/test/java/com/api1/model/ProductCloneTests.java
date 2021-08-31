package com.api1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductCloneTests {
	
	ProductClone productClone;
	Date today = Date.valueOf(LocalDate.now());

	@BeforeEach
	public void setup() {
		productClone = new ProductClone();
		productClone.setCloneId(1);
		productClone.setCloneProductId("A1");
		productClone.setCloneProductName("Rice");
		productClone.setCloneProductExpiryDate(today);
	}

	@Test
	public void getId() {

		assertEquals(1, productClone.getCloneId(), "getCloneId not implemented properly");
	}

	@Test
	public void getProductId() {
		assertEquals("A1", productClone.getCloneProductId(), "getCloneProductId not implemented properly");
	}

	@Test
	public void getProductName() {
		assertEquals("Rice", productClone.getCloneProductName(), "getCloneProductName not implemented properly");
	}

	@Test
	public void getProductExpiryDate() {
		assertEquals(today, productClone.getCloneProductExpiryDate(), "getCloneProductExpiryDate not implemented properly");
	}

	@Test
	public void toStringTest() {
		assertEquals("ProductClone [cloneId=1, cloneProductId=A1, cloneProductName=Rice, cloneProductExpiryDate=" + today + "]",
				productClone.toString(), "toString not implemented properly");
	}

}
