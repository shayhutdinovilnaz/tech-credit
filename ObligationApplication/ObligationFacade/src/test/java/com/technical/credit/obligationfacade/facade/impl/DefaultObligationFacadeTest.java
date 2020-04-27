package com.technical.credit.obligationfacade.facade.impl;

import com.technical.credit.obligationfacade.converter.impl.ObligationConverter;
import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.ObligationService;
import com.technical.credit.obligationservice.service.SearchQuery;
import com.technical.credit.obligationservice.service.SessionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    @Mock
    SessionService sessionService;

    @Mock
    GenericInstanceFactory genericInstanceFactory;

    @Test
    public void testSaveSuccess() {
        final ObligationData incomeObligationData = mock(ObligationData.class);
        final ObligationData outcomeObligationData = mock(ObligationData.class);
        final ObligationModel obligationModel = mock(ObligationModel.class);
        final UserModel currentUser = mock(UserModel.class);
        final long userId = 1L;

        when(obligationConverter.reverseConvert(incomeObligationData)).thenReturn(obligationModel);
        when(obligationConverter.convert(obligationModel)).thenReturn(outcomeObligationData);
        when(sessionService.getCurrentUser()).thenReturn(currentUser);
        when(currentUser.getId()).thenReturn(userId);

        final DefaultObligationFacade underTest = new DefaultObligationFacade(obligationConverter, obligationService, sessionService, genericInstanceFactory);
        Assert.assertNotNull(underTest.save(incomeObligationData));
        verify(obligationService).save(obligationModel);
        verifyNoMoreInteractions(obligationService);
        verify(obligationConverter).reverseConvert(incomeObligationData);
        verify(obligationConverter).convert(obligationModel);
        verifyNoMoreInteractions(obligationConverter);
        verify(sessionService).getCurrentUser();
        verifyNoMoreInteractions(sessionService);
        verify(obligationModel).setUserId(userId);
        verify(currentUser).getId();
        verifyNoMoreInteractions(currentUser);
        verifyZeroInteractions(incomeObligationData);
        verifyZeroInteractions(outcomeObligationData);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveNullableObligation() {
        new DefaultObligationFacade(obligationConverter, obligationService, sessionService, genericInstanceFactory).save(null);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testSaveExceptionInConverter() {
        final ObligationData obligationData = mock(ObligationData.class);

        when(obligationConverter.reverseConvert(obligationData)).thenThrow(ModelNotFoundException.class);

        new DefaultObligationFacade(obligationConverter, obligationService, sessionService, genericInstanceFactory).save(obligationData);
    }

    @Test
    public void testSearchById() {
        final long id = 1L;
        final ObligationModel obligationModel = mock(ObligationModel.class);
        final ObligationData obligationData = mock(ObligationData.class);

        when(obligationService.getById(id)).thenReturn(obligationModel);
        when(obligationConverter.convert(obligationModel)).thenReturn(obligationData);

        Assert.assertNotNull(new DefaultObligationFacade(obligationConverter, obligationService, sessionService, genericInstanceFactory).search(id));
        verify(obligationService).getById(id);
        verifyNoMoreInteractions(obligationService);
        verify(obligationConverter).convert(obligationModel);
        verifyNoMoreInteractions(obligationConverter);
        verifyZeroInteractions(sessionService);
        verifyZeroInteractions(obligationData);
        verifyZeroInteractions(obligationModel);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testSearchByIdNotFound() {
        final long id = 1L;

        when(obligationService.getById(id)).thenThrow(ModelNotFoundException.class);

        new DefaultObligationFacade(obligationConverter, obligationService, sessionService, genericInstanceFactory).search(id);
    }

    @Test
    public void testDelete() {
        final long id = 1L;
        final ObligationModel obligation = mock(ObligationModel.class);

        when(obligationService.getById(id)).thenReturn(obligation);

        new DefaultObligationFacade(obligationConverter, obligationService, sessionService, genericInstanceFactory).delete(id);
        verify(obligationService).getById(id);
        verify(obligationService).delete(obligation);
        verifyNoMoreInteractions(obligationService);
        verifyZeroInteractions(obligationConverter);
        verifyZeroInteractions(sessionService);
        verifyZeroInteractions(obligation);
    }

    @Test(expected = ModelNotFoundException.class)
    public void testDeleteNotExistObligation() {
        final long id = 1L;

        when(obligationService.getById(id)).thenThrow(ModelNotFoundException.class);

        new DefaultObligationFacade(obligationConverter, obligationService, sessionService, genericInstanceFactory).delete(id);
    }

    @Test
    public void testSearch() {
        final String freeText = "Free text";
        final String sortField = "Sort field";
        final boolean desc = true;
        final int page = 0;
        final int limit = 100;
        final SearchQuery searchQuery = mock(SearchQuery.class);
        final List<ObligationModel> obligations = mock(ArrayList.class);
        final UserModel currentUser = mock(UserModel.class);
        final ObligationModel obligationModel = mock(ObligationModel.class);
        final ObligationData obligationData = mock(ObligationData.class);

        when(genericInstanceFactory.getInstance(SearchQuery.class)).thenReturn(searchQuery);
        when(sessionService.getCurrentUser()).thenReturn(currentUser);
        when(obligationService.search(searchQuery, currentUser)).thenReturn(obligations);
        when(obligations.stream()).thenReturn(Stream.of(obligationModel));
        when(obligationConverter.convert(obligationModel)).thenReturn(obligationData);

        final DefaultObligationFacade underTest = new DefaultObligationFacade(obligationConverter, obligationService, sessionService, genericInstanceFactory);
        final List<ObligationData> result = underTest.search(freeText, sortField, desc, page, limit);
        Assert.assertEquals(1, result.size());
        verify(searchQuery).setFreeText(freeText);
        verify(searchQuery).setSortField(sortField);
        verify(searchQuery).setDesc(desc);
        verify(searchQuery).setPage(page);
        verify(searchQuery).setLimit(limit);
        verify(genericInstanceFactory).getInstance(SearchQuery.class);
        verifyNoMoreInteractions(genericInstanceFactory);
        verify(sessionService).getCurrentUser();
        verifyNoMoreInteractions(sessionService);
        verify(obligationService).search(searchQuery, currentUser);
        verifyNoMoreInteractions(obligationService);
        verify(obligationConverter).convert(obligationModel);
        verifyNoMoreInteractions(obligationConverter);
        verifyZeroInteractions(obligationData, obligationModel);
    }

}