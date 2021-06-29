package com.api1.model;

/**
 * 
 * ResponseHandle Class handles the response
 *
 */
public class ResponseHandler {

	String responseType;
	String responseMessage;
	Product productResponse;

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

	public Product getProductResponse() {
		return productResponse;
	}

	public void setProductResponse(Product productResponse) {
		this.productResponse = productResponse;
	}

	@Override
	public String toString() {
		return "ResponseHandler [responseType=" + responseType + ", responseMessage=" + responseMessage
				+ ", productResponse=" + productResponse + "]";
	}

}
