package com.technical.credit.webapp.configuaration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOAuth2Sso
// добавляем порядок, чтобы Filters был создан WebSecurityConfigurerAdapter и имеел приоритет над Filters ,
// созданными другими WebSecurityConfigurerAdapters .
// ResouceServerConfiguration , класс конфигурации, запускаемый @EnableResourceServer , задает значение по умолчанию order ,
// равное 3, тогда как WebSecurityConfigureAdapter имеет значение по умолчанию order , равное 100.
@Order(value = 0)
@RequiredArgsConstructor
public class ObligationWebApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
