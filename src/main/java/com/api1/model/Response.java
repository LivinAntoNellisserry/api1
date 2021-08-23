package com.api1.model;

/**
 * 
 * Response Class. Contains product and status as variables.
 *
 */
public class Response {

	Product product;
	String status;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Response [product=" + product + ", status=" + status + "]";
	}

}
