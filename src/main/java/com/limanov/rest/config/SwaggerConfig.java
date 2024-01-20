package com.limanov.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI( @Value("${swagger_server_url}") String swaggerServerUrl) {
        return new OpenAPI()
                .servers(List.of(new Server().url(swaggerServerUrl).description("With my description")))
                .info(new Info().title("My dog API"));
    }
}
