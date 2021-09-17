package com.api1.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.api1.exception.ProductAlreadyPresentException;
import com.api1.exception.ProductNotDeletedException;
import com.api1.exception.ProductNotFoundException;
import com.api1.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Api1ControllerExceptionTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ProductServiceImpl service;

	@Test
	public void getProductNotPresent() throws Exception {
		when(service.getProductById(Mockito.anyString())).thenThrow(new ProductNotFoundException("PRODUCT ABSENT"));
		mockMvc.perform(MockMvcRequestBuilders.get("/api1/search/F1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteProductNotPresent() throws Exception {
		when(service.deleteProduct(Mockito.anyString()))
				.thenThrow(new ProductNotDeletedException("PRODUCT NOT PRESENT"));
		mockMvc.perform(MockMvcRequestBuilders.get("/api1/delete/G1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void deleteProductNotExpired() throws Exception {
		when(service.deleteProduct(Mockito.anyString()))
				.thenThrow(new ProductNotDeletedException("PRODUCT NOT EXPIRED"));
		mockMvc.perform(MockMvcRequestBuilders.get("/api1/delete/G1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void addProductAlreadyPresent() throws Exception {
		when(service.addProduct(Mockito.any()))
				.thenThrow(new ProductAlreadyPresentException("PRODUCT ALREADY PRESENT"));
		mockMvc.perform(MockMvcRequestBuilders.post("/api1/add")
				.content("{\r\n" + "        \"id\": 1,\r\n" + "        \"productId\": \"F1\",\r\n"
						+ "        \"productName\": \"Burger\",\r\n"
						+ "        \"productExpiryDate\": \"2021-12-16\"\r\n" + "    }")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void updateProductNotPresent() throws Exception {

		when(service.updateProduct(Mockito.any())).thenThrow(new ProductNotFoundException("PRODUCT NOT PRESENT"));
		mockMvc.perform(MockMvcRequestBuilders.post("/api1/update")
				.content("{\r\n" + "        \"id\": 1,\r\n" + "        \"productId\": \"F1\",\r\n"
						+ "        \"productName\": \"Burger\",\r\n"
						+ "        \"productExpiryDate\": \"2021-12-16\"\r\n" + "    }")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void expiredDate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api1/update")
				.content("{\r\n" + "        \"id\": 1,\r\n" + "        \"productId\": \"F1\",\r\n"
						+ "        \"productName\": \"Burger\",\r\n"
						+ "        \"productExpiryDate\": \"2020-12-16\"\r\n" + "    }")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void invalidDateFormat() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api1/update")
				.content("{\r\n" + "        \"id\": 1,\r\n" + "        \"productId\": \"F1\",\r\n"
						+ "        \"productName\": \"Burger\",\r\n"
						+ "        \"productExpiryDate\": \"16-12-2021\"\r\n" + "    }")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
