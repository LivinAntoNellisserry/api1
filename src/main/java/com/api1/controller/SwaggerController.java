package com.api1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * SwaggerController Class.
 *
 */
@Controller
public class SwaggerController {

	@GetMapping("/")
	public String callSwaggerUI() {
		return "redirect:/swagger-ui.html";
	}
}
