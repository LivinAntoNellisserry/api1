package com.api1.model;

/**
 * 
 * ResponseHandler Class. Handles the response from an end point. Contains
 * responseType, responseMessage and productClone as variables.
 *
 */
public class Api2Response {

	String responseType;
	String responseMessage;
	ProductClone productClone;

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

	public ProductClone getProductClone() {
		return productClone;
	}

	public void setProductClone(ProductClone productClone) {
		this.productClone = productClone;
	}

	@Override
	public String toString() {
		return "ResponseHandler [responseType=" + responseType + ", responseMessage=" + responseMessage
				+ ", productClone=" + productClone + "]";
	}
}
