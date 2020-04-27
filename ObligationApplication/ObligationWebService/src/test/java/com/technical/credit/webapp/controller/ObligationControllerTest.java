package com.technical.credit.webapp.controller;


import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.facade.ObligationFacade;
import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ObligationControllerTest extends AbstractControllerTest {

    @MockBean
    ObligationFacade obligationFacade;

    @Test
    public void testSave() throws Exception {
        final ObligationData requestObligationData = new ObligationData();
        final ObligationData responseObligationData = new ObligationData();
        final String jsonBody = parseToJson(requestObligationData);
        final String url = "/api/v1/obligations";

        when(obligationFacade.save(any(ObligationData.class))).thenReturn(responseObligationData);

        mvc.perform(MockMvcRequestBuilders.put(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)).andExpect(MockMvcResultMatchers.status().isCreated());
        verify(obligationFacade).save(requestObligationData);
        verifyNoMoreInteractions(obligationFacade);
    }

    @Test
    public void testSaveWithNotExistAttribute() throws Exception {
        final ObligationData requestObligationData = new ObligationData();
        final String jsonBody = parseToJson(requestObligationData);
        final String url = "/api/v1/obligations";

        when(obligationFacade.save(any(ObligationData.class))).thenThrow(ModelNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.put(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonBody)).andExpect(MockMvcResultMatchers.status().isBadRequest());
        verify(obligationFacade).save(requestObligationData);
        verifyNoMoreInteractions(obligationFacade);
    }

    @Test
    public void testSearchById() throws Exception {
        final String url = "/api/v1/obligations/1";
        final ObligationData obligation = new ObligationData();

        when(obligationFacade.search(1L)).thenReturn(obligation);

        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        verify(obligationFacade).search(1L);
        verifyNoMoreInteractions(obligationFacade);
    }

    @Test
    public void testSearchByIdNotFound() throws Exception {
        final String url = "/api/v1/obligations/1";

        when(obligationFacade.search(1L)).thenThrow(ModelNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        verify(obligationFacade).search(1L);
        verifyNoMoreInteractions(obligationFacade);
    }

    @Test
    public void testSuccessfulDeleteBookById() throws Exception {
        final String url = "/api/v1/obligations/1";

        mvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        verify(obligationFacade).delete(1L);
        verifyNoMoreInteractions(obligationFacade);
    }

    @Test
    public void testAttemptToDeleteNotExistBook() throws Exception {
        final String url = "/api/v1/obligations/1";

        doThrow(ModelNotFoundException.class).when(obligationFacade).delete(1L);

        mvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        verify(obligationFacade).delete(1l);
        verifyNoMoreInteractions(obligationFacade);
    }

    @Test
    public void testSearchByQuery() throws Exception {
        final String url = "/api/v1/obligations/search";
        final String query = "Free text";
        final String sortField = "Sort field";
        final boolean desc = true;
        final int page = 0;
        final int limit = 100;
        List<ObligationData> obligations = Collections.singletonList(new ObligationData());

        when(obligationFacade.search(query, sortField, desc, page, limit)).thenReturn(obligations);

        final String responseBody = mvc.perform(MockMvcRequestBuilders.get(url)
                .param("query", query)
                .param("sortField", sortField)
                .param("desc", Boolean.toString(desc))
                .param("page", "0")
                .param("limit", "100"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Assert.assertEquals(1, parseFromJson(responseBody, ArrayList.class).size());
        verify(obligationFacade).search(query, sortField, desc, page, limit);
        verifyNoMoreInteractions(obligationFacade);
    }

}