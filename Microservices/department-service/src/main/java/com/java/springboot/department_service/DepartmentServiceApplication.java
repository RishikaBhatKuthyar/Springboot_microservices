package com.java.springboot.department_service;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
				description = "Spring Boot Department Management Documentation",
				url="http://localhost:8080/swagger-ui/index.html"
		)
)
public class DepartmentServiceApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
