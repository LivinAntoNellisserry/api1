package com.api1.controller;

import static org.mockito.Mockito.when;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.api1.model.Product;
import com.api1.model.View;
import com.api1.service.ProductService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest
class Api1ControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private ProductService service;

	@InjectMocks
	private Api1Controller controller;

	Product product;
	View view;

	@BeforeEach
	public void setUp() {
		product = new Product();
		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate("2021-08-12");

		view = new View();
		view.setProduct(product);
		view.setStatus("NOT EXPIRED");

	}

	@Test
	public void getProductByIdTest() throws Exception {
		when(service.getProductById(product.getProductId())).thenReturn(view);
		mock.perform(MockMvcRequestBuilders.get("/api1/search/G1").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(product))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void addProductTest() throws Exception {
		when(service.addProduct(product)).thenReturn(product);
		mock.perform(MockMvcRequestBuilders.post("/api1/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(product))).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void updateProductTest() throws Exception {
		when(service.updateProduct(product)).thenReturn(product);
		mock.perform(MockMvcRequestBuilders.post("/api1/update").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(product))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void deleteProductTest() throws Exception {
		product.setProductExpiryDate("2020-06-25");
		when(service.deleteProduct(product.getProductId())).thenReturn("SUCCESS");
		mock.perform(MockMvcRequestBuilders.get("/api1/delete/G1").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(product))).andExpect(MockMvcResultMatchers.status().isOk());

	}

}
