package com.api1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api1.exceptions.ProductExpiryDateException;
import com.api1.exceptions.ProductIdException;
import com.api1.exceptions.ProductNameException;
import com.api1.model.Product;
import com.api1.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
@RequestMapping("/api1")
public class Api1Controller {

	@Autowired
	ProductService serv;

	@GetMapping("/search/{productId}")
	@ApiOperation(value = "Search by Product ID")
	public ResponseEntity<?> getProductById(@PathVariable String productId) {
		Product product = new Product();
		try {
			product = serv.getProductById(productId);
			try {
			if (product.equals(null)){}
			}catch(NullPointerException e) {
				return new ResponseEntity<String>("The Product was not found",HttpStatus.BAD_REQUEST); 
			}
		} catch (ProductIdException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "Add Product")
	public  ResponseEntity<?> addProduct(@RequestBody Product product){
		Product updatedProduct = new Product();
		
		try {
			updatedProduct = serv.addProduct(product);
			if (product.getProductName().equals(null))
			{
				return new ResponseEntity<String>("Product Not Added to Database Unfortunately",HttpStatus.NO_CONTENT); 
			}
		} catch (ProductIdException | ProductNameException | ProductExpiryDateException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);
	}

}
