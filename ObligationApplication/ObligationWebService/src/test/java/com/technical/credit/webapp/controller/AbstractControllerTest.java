package com.technical.credit.webapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public abstract class AbstractControllerTest {
    @Autowired
    protected MockMvc mvc;

    protected <T> T parseFromJson(final String json, final Class<T> clazz) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    protected String parseToJson(final Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}