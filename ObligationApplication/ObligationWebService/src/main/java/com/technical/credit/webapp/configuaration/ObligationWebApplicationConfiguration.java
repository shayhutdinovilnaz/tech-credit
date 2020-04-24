package com.technical.credit.webapp.configuaration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@ComponentScan(basePackages = "com.technical.credit")
@EntityScan(basePackages = "com.technical.credit.obligationservice.model")
@EnableJpaRepositories(basePackages = "com.technical.credit.obligationservice.repository")
@EnableSwagger2
public class ObligationWebApplicationConfiguration {
    @Bean
    public Docket bookStorageInfoApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.technical.credit.webapp.controller"))
                .paths(regex("/api.*"))
                .build();
    }
}
