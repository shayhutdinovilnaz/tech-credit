package account.controller;

import account.config.AuthorizationServerConfiguration;
import account.config.AuthorizationServerSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {AuthorizationServerSecurityConfiguration.class, AuthorizationServerConfiguration.class}))
public abstract class AbstractControllerTest {
    @Autowired
    protected MockMvc mvc;
}