package com.wrapper.castillaYLeon.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Wrapper de Castilla y Leon")
                        .version("1.0.0")
                        .description("Documentación de el extractor de Euskadi(.json"));
    }
}
