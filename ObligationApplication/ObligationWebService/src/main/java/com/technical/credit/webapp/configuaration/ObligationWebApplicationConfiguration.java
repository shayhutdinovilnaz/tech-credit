package com.technical.credit.webapp.configuaration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 16.04.2020
 */
@Configuration
@ComponentScan(basePackages = "com.technical.credit")
@EntityScan(basePackages = "com.technical.credit.obligationservice.model")
@EnableJpaRepositories(basePackages = "com.technical.credit.obligationservice.repository")
public class ObligationWebApplicationConfiguration {
}
