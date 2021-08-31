package com.api1.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api1.exception.ProductAlreadyPresentException;
import com.api1.exception.ProductNotDeletedException;
import com.api1.exception.ProductNotFoundException;
import com.api1.model.Product;
import com.api1.model.Response;
import com.api1.service.ProductService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest
class Api1ControllerTests {

	@Autowired
	private MockMvc mock;

	@MockBean
	private ProductService service;

	@InjectMocks
	private Api1Controller controller;

	Product product;
	Response response;

	@BeforeEach
	public void setUp() {

		product = new Product();
		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate(Date.valueOf(LocalDate.now()).toString());

		response = new Response();
		response.setProduct(product);
		response.setStatus("NOT EXPIRED");

	}

	@AfterEach
	public void tearDown() {
		product = null;
		response = null;
	}

	@Test
	public void getProductById() throws Exception {
		System.out.println("1");
		when(service.getProductById(product.getProductId())).thenReturn(response);
		mock.perform(MockMvcRequestBuilders.get("/api1/search/F1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void addProduct() throws Exception {
		System.out.println("2");
		when(service.addProduct(product)).thenReturn(response);
		mock.perform(MockMvcRequestBuilders.post("/api1/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(product)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void updateProduct() throws Exception {
		System.out.println("3");
		when(service.updateProduct(product)).thenReturn(response);
		mock.perform(MockMvcRequestBuilders.post("/api1/update").content(new Gson().toJson(product))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteProduct() throws Exception {
		System.out.println("4");
		product.setProductExpiryDate("2020-06-25");
		when(service.deleteProduct(product.getProductId())).thenReturn("SUCCESS");
		mock.perform(MockMvcRequestBuilders.get("/api1/delete/G1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void getProductNotPresent() throws Exception {
		System.out.println("5");
		when(service.getProductById("F1")).thenThrow(new ProductNotFoundException("PRODUCT ABSENT"));
		mock.perform(MockMvcRequestBuilders.get("/api1/search/F1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void addProductAlreadyPresent() throws Exception {
		System.out.println("6");
		when(service.addProduct(product)).thenThrow(new ProductAlreadyPresentException("PRODUCT ALREADY PRESENT"));
		mock.perform(MockMvcRequestBuilders.post("/api1/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(product)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void updateProductNotPresent() throws Exception {
		System.out.println("7");
		when(service.updateProduct(product)).thenThrow(new ProductNotFoundException("PRODUCT NOT PRESENT"));
		mock.perform(MockMvcRequestBuilders.post("/api1/update").content(new Gson().toJson(product))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteProductNotPresent() throws Exception {
		System.out.println("8");
		when(service.deleteProduct(product.getProductId()))
				.thenThrow(new ProductNotDeletedException("PRODUCT NOT PRESENT"));
		mock.perform(MockMvcRequestBuilders.get("/api1/delete/G1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void deleteProductNotExpired() throws Exception {
		System.out.println("9");
		when(service.deleteProduct(product.getProductId()))
				.thenThrow(new ProductNotDeletedException("PRODUCT NOT EXPIRED"));
		mock.perform(MockMvcRequestBuilders.get("/api1/delete/G1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
