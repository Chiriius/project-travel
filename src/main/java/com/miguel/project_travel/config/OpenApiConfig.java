package com.miguel.project_travel.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Pojeect Travel API" ,version = "1.0",description = "Documentacion basica para la api ")
)
public class OpenApiConfig {


}
