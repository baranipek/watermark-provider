package com.watermark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("watermark")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/watermark.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WaterMarkResponseDto REST Sample with Swagger")
                .description("WaterMarkResponseDto Example")
                .contact("Baran İpek")
                .license("Apache License Version 2.0")
                .version("2.0")
                .build();
    }
}


