package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springShopOpenApi() {
		OpenAPI openAPI = new OpenAPI();

		Info info = new Info();
		info.title("Biblioteca Acelera G&P");
		info.version("v0.0.1");
		info.description(
				"<h2> Descreve a comunicação com o sistema da biblioteca Acelera G&P</h2> <p> Aceito <strong>HTML</strong> normalmente </p>");
		openAPI.info(info);
		
		openAPI.addTagsItem(new Tag().name("Livros").description("Gerencia os livros da biblioteca") );
		openAPI.addTagsItem(new Tag().name("Autores").description("Gerencia os autores da biblioteca") );

		return openAPI;
	}

}
