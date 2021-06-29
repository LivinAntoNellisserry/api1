package com.api1.model;
/**
 * 
 * View Class to get product as well as status
 *
 */
public class View {

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
		return "View [product=" + product + ", status=" + status + "]";
	}

}
