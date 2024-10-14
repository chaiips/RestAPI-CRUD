package com.mp.appusermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AppusermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppusermanagementApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
				.allowedMethods("POST", "PUT", "GET", "DELETE", "OPTIONS")
				.allowedOrigins("http://localhost:8081");
			}
		};
	}
}
