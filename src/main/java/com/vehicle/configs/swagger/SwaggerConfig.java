package com.vehicle.configs.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.vehicle.constants.SwaggerConstants.DESCRIPTION;
import static com.vehicle.constants.SwaggerConstants.LICENSE_NAME;
import static com.vehicle.constants.SwaggerConstants.LICENSE_URL;
import static com.vehicle.constants.SwaggerConstants.TERMS_OF_SERVICE;
import static com.vehicle.constants.SwaggerConstants.TITLE;
import static com.vehicle.constants.SwaggerConstants.VERSION;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION)
                        .termsOfService(TERMS_OF_SERVICE)
                        .license(new License().name(LICENSE_NAME).url(LICENSE_URL)));
    }
}
