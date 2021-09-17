//package com.api1.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.api1.model.Api1Response;
//
//import okhttp3.mockwebserver.MockResponse;
//import okhttp3.mockwebserver.MockWebServer;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ProductServiceTests1 {
//
//	private MockWebServer mockWebServer;
//	private ProductServiceImpl productServiceImpl;
//
//	@BeforeEach
//	public void setup() throws IOException {
//		this.mockWebServer = new MockWebServer();
//		this.mockWebServer.start();
//		this.productServiceImpl = new ProductServiceImpl();
//	}
//
//	@Test
//	public void getProductById() throws Exception {
//		MockResponse mockResponse = new MockResponse().addHeader("Content-Type", "application/json; charset=utf-8")
//				.setBody("{\r\n" + "    \"product\": {\r\n" + "        \"id\": 1,\r\n"
//						+ "        \"productId\": \"F1\",\r\n" + "        \"productName\": \"Burger\",\r\n"
//						+ "        \"productExpiryDate\": \"2021-12-16\"\r\n" + "    },\r\n"
//						+ "    \"status\": \"NOT EXPIRED\"\r\n" + "}")
//				.throttleBody(16, 5, TimeUnit.SECONDS);
//		mockWebServer.enqueue(mockResponse);
//		Api1Response api1Response = productServiceImpl.getProductById("F1");
//		assertEquals("F1", api1Response.getProduct().getProductId());
//	}
//}
