package com.example.ApiClima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableCaching
public class ApiClimaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiClimaApplication.class, args);
	}

}
