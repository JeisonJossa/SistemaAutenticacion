package com.dev.sistemaautenticacion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de Swagger para la documentación de la API.
 * Utiliza la especificación OpenAPI 3 para generar y personalizar la documentación.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura los detalles de la API utilizando OpenAPI.
     *
     * @return Una instancia de OpenAPI con la información personalizada.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Configuración de servidores (por ejemplo, entorno local o producción)
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor local"),
                        new Server().url("https://api.miapp.com").description("Servidor de producción")
                ))
                // Información general de la API
                .info(new Info()
                        .title("Sistema de Autenticación API")
                        .description("API REST para la gestión de usuarios y autenticación")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Soporte de la API")
                                .url("https://www.miapp.com/soporte")
                                .email("jeisonjossa@miapp.com"))
                        .license(new License()
                                .name("Licencia MIT")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
