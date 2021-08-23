package com.api1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api1.exception.ProductAlreadyPresentException;
import com.api1.exception.ProductNotDeletedException;
import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;
import com.api1.model.Response;
import com.api1.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * Api1Controller Class. Contains all the Handler Methods
 *
 */
@RestController
@Api
@RequestMapping("/api1")
public class Api1Controller {

	@Autowired
	ProductService serv;

	/**
	 * Returns a response for productId.
	 * 
	 * @param productId
	 * @return response
	 */
	@GetMapping("/search/{productId}")
	@ApiOperation(value = "Search by Product ID")
	public ResponseEntity<Response> getProductById(@PathVariable String productId) {
		try {
			return new ResponseEntity<Response>(serv.getProductById(productId), HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			Response response = new Response();
			response.setProduct(null);
			response.setStatus(e.getMessage());
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
	}

	/**
	 * Saves the product and returns the response containing the added product if it
	 * was added.
	 * 
	 * @param product
	 * @return response
	 */
	@PostMapping("/add")
	@ApiOperation(value = "Add Product")
	public ResponseEntity<Response> addProduct(@RequestBody @Valid Product product) {

		try {
			return new ResponseEntity<Response>(serv.addProduct(product), HttpStatus.CREATED);
		} catch (ProductAlreadyPresentException e) {
			Response response = new Response();
			response.setProduct(null);
			response.setStatus(e.getMessage());
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

	}

	/**
	 * Updates the product if the product was present and returns the response
	 * containing updated product.
	 * 
	 * @param product
	 * @return response
	 */
	@PostMapping("/update")
	@ApiOperation(value = "Update Product")
	public ResponseEntity<Response> updateProduct(@RequestBody @Valid Product product) {
		try {
			return new ResponseEntity<Response>(serv.updateProduct(product), HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			Response response = new Response();
			response.setProduct(null);
			response.setStatus(e.getMessage());
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
	}

	/**
	 * Deletes the product by Id and returns the message.
	 * 
	 * @param productId
	 * @return string
	 */
	@GetMapping("/delete/{productId}")
	@ApiOperation(value = "Delete Product")
	public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
		try {
			return new ResponseEntity<String>(serv.deleteProduct(productId), HttpStatus.OK);
		} catch (ProductNotDeletedException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}

}
