package com.api1.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 
 * WebClientConfig Class.
 *
 */
@Configuration
public class WebClientConfig {

	@Bean
	@LoadBalanced
	WebClient.Builder builder() {
		return WebClient.builder();
	}

	@Bean
	WebClient webClient(WebClient.Builder builder) {
		return builder.build();
	}
}
