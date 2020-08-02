package account.controller;


import account.facade.AccountFacade;
import account.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE))
@AutoConfigureMockMvc(addFilters = false)
public abstract class AbstractControllerTest {
    @Autowired
    protected MockMvc mvc;

    @MockBean
    UserFacade userFacade;

    @MockBean
    UserDetailsService userDetailsService;

    @MockBean
    AccountFacade accountFacade;
}