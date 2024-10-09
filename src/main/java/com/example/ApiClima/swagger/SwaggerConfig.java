package com.example.ApiClima.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("APIClima")
                        .version("1.0")
                        .description("Me toco consumir openweathermap para realizar una api de clima." +
                                " Ademas cuenta con un limitador de peticiones y implementacion de cache"));
    }
}
