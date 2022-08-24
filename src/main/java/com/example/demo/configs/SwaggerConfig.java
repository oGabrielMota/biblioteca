package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springShopOpenApi() {
		OpenAPI openAPI = new OpenAPI();
		
		return openAPI;
	}

}
