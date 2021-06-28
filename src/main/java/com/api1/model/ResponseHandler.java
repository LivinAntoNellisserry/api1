package com.api1.model;


public class ResponseHandler {

	String responseType;
	String responseMessage;
	Product responseProduct;

	public ResponseHandler() {
	}

	public ResponseHandler(String responseType, String responseMessage, Product responseProduct) {
		this.responseType = responseType;
		this.responseMessage = responseMessage;
		this.responseProduct = responseProduct;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Product getResponseProduct() {
		return responseProduct;
	}

	public void setResponseProduct(Product responseProduct) {
		this.responseProduct = responseProduct;
	}

}
