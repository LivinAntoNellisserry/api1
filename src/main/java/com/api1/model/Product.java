package com.api1.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.api1.validator.ProductExpiryDate;

public class Product {

	Integer id;
	@NotNull(message = "The Product Id cannot be empty")
	@Size(max = 10, min = 1, message = "The id should be in range of 1 to 10 characters")
	String productId;
	@NotNull(message = "The Product Name cannot be empty")
	@Size(max = 10, min = 1, message = "The name should be in range of 1 to 10 characters")
	String productName;
	@NotNull(message = "Enter the date in YYYY-MM-DD format")
	@ProductExpiryDate
	String productExpiryDate;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductExpiryDate() {
		return productExpiryDate;
	}

	public void setProductExpiryDate(String productExpiryDate) {
		this.productExpiryDate = productExpiryDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productId=" + productId + ", productName=" + productName
				+ ", productExpiryDate=" + productExpiryDate + "]";
	}

}
