package com.example.HappySocialMedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

// The Swagger will help us to easily view the architecture of the back end on web
public class SwaggerConfiguration {
    @Bean
    public Docket happySocialMediaApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Happy Tweet API")
                .version("1.0")
                .description("API for Happy Tweet Application")
                .contact(new Contact("Phu Nguyen", "https://www.facebook.com/jacknguyen0216/"
                        , "s3811248@rmit.edu.vn"))
                .license("Apache License Version 2.0")
                .build();
    }
}
