// File: chakray/src/main/java/com/chakray/chakray/config/OpenApiConfig.java
package com.chakray.chakray.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI / Swagger para la documentación de la API.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Users REST API")
                        .version("1.0.0")
                        .description(
                                "API REST desarrollada con Spring Boot para la gestión de usuarios. "
                              + "Incluye autenticación, filtrado dinámico, ordenamiento y validación de datos."
                        )
                        .contact(new Contact()
                                .name("Arturo Raziel Chavez")
                                .email("rratel76@gmail.com")
                                .url("https://github.com/")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                );
    }
}