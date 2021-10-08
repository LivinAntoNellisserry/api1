package com.api1.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api1.model.Product;
import com.api1.model.Api1Response;
import com.api1.service.ProductService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest
class Api1ControllerTests {

	@Autowired
	private MockMvc mock;

	@MockBean
	private ProductService serv;

	Product product;
	Api1Response response;

	@BeforeEach
	public void setUp() {

		product = new Product();
		product.setId(1);
		product.setProductId("G1");
		product.setProductName("Noodles");
		product.setProductExpiryDate(Date.valueOf(LocalDate.now()).toString());

		response = new Api1Response();
		response.setProduct(product);
		response.setStatus("NOT EXPIRED");

	}

	@Test
	public void getProductById() throws Exception {

		when(serv.getProductById(Mockito.anyString())).thenReturn(response);
		mock.perform(MockMvcRequestBuilders.get("/api1/search/F1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void addProduct() throws Exception {

		response.setStatus("PRODUCT SAVED");
		when(serv.addProduct(Mockito.any())).thenReturn(response);
		mock.perform(MockMvcRequestBuilders.post("/api1/add").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(product)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void updateProduct() throws Exception {
		response.setStatus("PRODUCT UPDATED");
		when(serv.updateProduct(Mockito.any())).thenReturn(response);
		mock.perform(MockMvcRequestBuilders.post("/api1/update").content(new Gson().toJson(product))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteProduct() throws Exception {
		when(serv.deleteProduct(Mockito.anyString())).thenReturn("SUCCESS");
		mock.perform(MockMvcRequestBuilders.get("/api1/delete/G1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

}
