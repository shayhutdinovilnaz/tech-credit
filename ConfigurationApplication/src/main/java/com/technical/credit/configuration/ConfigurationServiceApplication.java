package com.technical.credit.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.config.server.ConfigServerApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 29.03.2020
 */
@SpringBootApplication
@EnableConfigServer
@EnableConfigurationProperties
public class ConfigurationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
