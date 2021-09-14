package com.api1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.api1.model.Api1Response;

@SpringBootTest
public class Api1Test {

	@Autowired
	WebClient webClient;

	@Test
	public void getProductById() {
		Api1Response api1Response = webClient.get().uri("http://api-1/api1/search/F1").retrieve()
				.bodyToMono(Api1Response.class).block();
		
		System.out.println(api1Response);
	}

}
