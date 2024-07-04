package com.example.demo;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info=@Info(
				title = "Spring Boot REST API Documentation",
				description = "Has the above along with System Design",
				version = "v1.0",
				contact = @Contact(
						name = "Deepak Viswanadha",
						email="test@test.com",
						url = "test.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "test.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot and System Design doc",
				url = "test.com"
		)
)
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
