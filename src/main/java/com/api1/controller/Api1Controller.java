package com.api1.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.api1.exception.ProductAlreadyPresentException;
import com.api1.exception.ProductNotDeletedException;
import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;
import com.api1.model.Api1Response;
import com.api1.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Api1Controller Class. Contains all the Handler Methods
 *
 */
@RestController
@Slf4j
@Api
@RequestMapping("/api1")
public class Api1Controller {

	@Autowired
	ProductService serv;

	private final Logger log = LoggerFactory.getLogger(Api1Controller.class);
	private static final String HITAPI1 = "Hit Api1Controller";

	/**
	 * Returns a response for productId.
	 * 
	 * @param productId
	 * @return response
	 */
	@GetMapping("/search/{productId}")
	@ApiOperation(value = "Search by Product ID")
	public ResponseEntity<Api1Response> getProductById(@PathVariable String productId) {
		try {
			log.info(HITAPI1);
			log.info("Called getProductById");
			log.debug(productId);
			return new ResponseEntity<Api1Response>(serv.getProductById(productId), HttpStatus.OK);
		} catch (ProductNotFoundException | WebClientResponseException e) {
			Api1Response response = new Api1Response();
			response.setProduct(null);
			response.setStatus(e.getMessage());
			log.info("Exception occured executed catch block of getProductById in Api1Controller");
			if (log.isDebugEnabled()) {
				log.debug(e.toString());
			}
			return new ResponseEntity<Api1Response>(response, HttpStatus.OK);
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
	public ResponseEntity<Api1Response> addProduct(@RequestBody @Valid Product product) {

		try {
			log.info(HITAPI1);
			log.info("Called addProduct");
			if (log.isDebugEnabled()) {
				log.debug(product.toString());
			}
			return new ResponseEntity<Api1Response>(serv.addProduct(product), HttpStatus.OK);
		} catch (ProductAlreadyPresentException | WebClientResponseException e) {
			Api1Response response = new Api1Response();
			response.setProduct(null);
			response.setStatus(e.getMessage());
			log.info("Exception occured executed catch block of addProduct in Api1Controller");
			if (log.isDebugEnabled()) {
				log.debug(e.toString());
			}
			return new ResponseEntity<Api1Response>(response, HttpStatus.OK);
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
	public ResponseEntity<Api1Response> updateProduct(@RequestBody @Valid Product product) {
		try {
			log.info(HITAPI1);
			log.info("Called updateProduct");
			if (log.isDebugEnabled()) {
				log.debug(product.toString());
			}
			return new ResponseEntity<Api1Response>(serv.updateProduct(product), HttpStatus.OK);
		} catch (ProductNotFoundException | WebClientResponseException e) {
			Api1Response response = new Api1Response();
			response.setProduct(null);
			response.setStatus(e.getMessage());
			log.info("Exception occured executed catch block of updateProduct in Api1Controller");
			if (log.isDebugEnabled()) {
				log.debug(e.toString());
			}
			return new ResponseEntity<Api1Response>(response, HttpStatus.OK);
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
			log.info(HITAPI1);
			log.info("Called deleteProduct");
			log.debug(productId);
			return new ResponseEntity<String>(serv.deleteProduct(productId), HttpStatus.OK);
		} catch (ProductNotDeletedException | WebClientResponseException e) {
			log.info("Exception occured executed catch block of deleteProduct in Api1Controller");
			if (log.isDebugEnabled()) {
				log.debug(e.toString());
			}
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}

}
