package com.technical.credit.obligationfacade.facade.impl;

import com.technical.credit.obligationfacade.converter.impl.ObligationConverter;
import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.service.ObligationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultObligationFacadeTest {
    @Mock
    ObligationConverter obligationConverter;

    @Mock
    ObligationService obligationService;

    @Test
    public void testSaveSuccess() {
        final ObligationData incomeObligationData = mock(ObligationData.class);
        final ObligationData outcomeObligationData = mock(ObligationData.class);
        final ObligationModel obligationModel = mock(ObligationModel.class);

        when(obligationConverter.reverseConvert(incomeObligationData)).thenReturn(obligationModel);
        when(obligationConverter.convert(obligationModel)).thenReturn(outcomeObligationData);

        final DefaultObligationFacade underTest = new DefaultObligationFacade(obligationConverter, obligationService);
        Assert.assertNotNull(underTest.save(incomeObligationData));
        verify(obligationService).save(obligationModel);
        verifyNoMoreInteractions(obligationService);
        verify(obligationConverter).reverseConvert(incomeObligationData);
        verify(obligationConverter).convert(obligationModel);
        verifyNoMoreInteractions(obligationConverter);
        verifyZeroInteractions(incomeObligationData);
        verifyZeroInteractions(outcomeObligationData);
        verifyZeroInteractions(obligationModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNullableObligation() {
        new DefaultObligationFacade(obligationConverter, obligationService).save(null);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testSaveExceptionInConverter() {
        final ObligationData obligationData = mock(ObligationData.class);

        when(obligationConverter.reverseConvert(obligationData)).thenThrow(ModelNotFoundException.class);

        new DefaultObligationFacade(obligationConverter, obligationService).save(obligationData);
    }

}