package com.example.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication(scanBasePackages = "com.example.project")
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	@Bean
	CommandLineRunner logMappings(RequestMappingHandlerMapping mapping) {
		return args -> mapping.getHandlerMethods().forEach((k, v) ->
				System.out.println("MAPPING: " + k + " -> " + v));
	}
}
