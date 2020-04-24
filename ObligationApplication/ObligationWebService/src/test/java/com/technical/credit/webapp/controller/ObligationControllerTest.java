package com.technical.credit.webapp.controller;


import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.facade.ObligationFacade;
import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
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
        final ObligationData responseObligationData = new ObligationData();
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

}