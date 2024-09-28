package com.java.springboot.employee_service;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

@OpenAPIDefinition(
		info=@Info(
				title="Spring Boot REST API",
				description="Spring Boot REST API doc",
				version="v1.0",
				contact=@Contact(
						name="Rishika",
						email = "kuthyar.ri@northeastern.edu"
				),
				license = @License(
						name="Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Employee Management Documentation",
				url="http://localhost:8081/swagger-ui/index.html"
		)
)
public class EmployeeServiceApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
