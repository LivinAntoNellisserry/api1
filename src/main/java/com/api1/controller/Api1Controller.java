package com.api1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.api1.model.View;
import com.api1.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
@RequestMapping("/api1")
public class Api1Controller {

	@Autowired
	ProductService serv;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		String error = new String();
		for (FieldError fieldError : fieldErrors) {
			error = fieldError.getDefaultMessage();
		}
		return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/search/{productId}")
	@ApiOperation(value = "Search by Product ID")
	public ResponseEntity<?> getProductById(@PathVariable String productId) {
		try {
			return new ResponseEntity<View>(serv.getProductById(productId), HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PostMapping("/add")
	@ApiOperation(value = "Add Product")
	public ResponseEntity<?> addProduct(@RequestBody @Valid Product product) {

		try {
			return new ResponseEntity<Product>(serv.addProduct(product), HttpStatus.CREATED);
		} catch (ProductAlreadyPresentException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

	}

	@PostMapping("/update")
	@ApiOperation(value = "Update Product")
	public ResponseEntity<?> updateProduct(@RequestBody @Valid Product product) {
		try {
			return new ResponseEntity<Product>(serv.updateProduct(product), HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}

	@GetMapping("/delete/{productId}")
	@ApiOperation(value = "Delete Product")
	public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
		try {
			return new ResponseEntity<String>(serv.deleteProduct(productId), HttpStatus.OK);
		} catch (ProductNotDeletedException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}

}
