package com.api1.model;

import java.sql.Date;

public class Product {

	String productId;
	String productName;
	Date productExpiryDate;

	public Product() {
	}

	public Product(String productId, String productName, Date productExpiryDate) {
		this.productId = productId;
		this.productName = productName;
		this.productExpiryDate = productExpiryDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getProductExpiryDate() {
		return productExpiryDate;
	}

	public void setProductExpiryDate(Date productExpiryDate) {
		this.productExpiryDate = productExpiryDate;
	}
	
}
