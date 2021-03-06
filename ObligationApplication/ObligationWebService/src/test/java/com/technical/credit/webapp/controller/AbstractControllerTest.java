package com.technical.credit.webapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.credit.webapp.filter.RequestLanguageFilter;
import com.technical.credit.obligationfacade.facade.CategoryFacade;
import com.technical.credit.obligationfacade.facade.ObligationFacade;
import com.technical.credit.obligationservice.service.LanguageService;
import com.technical.credit.obligationservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

@WebMvcTest(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = RequestLanguageFilter.class))
@AutoConfigureMockMvc(addFilters = false)
public abstract class AbstractControllerTest {
    @Autowired
    protected MockMvc mvc;

    @MockBean
    protected ObligationFacade obligationFacade;

    @MockBean
    protected CategoryFacade categoryFacade;

    @MockBean
    LanguageService languageService;

    @MockBean
    RequestService requestService;

    @MockBean
    ResourceServerProperties resourceServerProperties;

    protected <T> T parseFromJson(final String json, final Class<T> clazz) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    protected String parseToJson(final Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}