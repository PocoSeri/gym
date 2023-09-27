package com.gym.gym.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    public static final String SECURITY_SCHEMA_NAME = "JWT Bearer Token";

    @Bean
    public OpenAPI openApiConfiguration(){
        return new OpenAPI().components(new Components().addSecuritySchemes(SECURITY_SCHEMA_NAME, new SecurityScheme()
                .name(SECURITY_SCHEMA_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        )).info(new Info());
    }
}
