package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.repository.ObligationRepository;
import com.technical.credit.obligationservice.service.SearchQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class DefaultObligationServiceTest extends AbstractModelServiceTest<ObligationModel> {

    @InjectMocks
    private DefaultObligationService underTest;

    @Mock
    private ObligationRepository obligationRepository;

    @Test
    public void testSearch() {
        final SearchQuery searchQuery = mock(SearchQuery.class);
        final UserModel currentUser = mock(UserModel.class);
        final String queryText = "freeText";
        final long userId = 1L;
        final List<ObligationModel> resultList = Collections.singletonList(new ObligationModel());

        when(searchQuery.getPage()).thenReturn(0);
        when(searchQuery.getLimit()).thenReturn(100);
        when(searchQuery.isDesc()).thenReturn(false);
        when(searchQuery.getSortField()).thenReturn("sortField");
        when(searchQuery.getFreeText()).thenReturn(queryText);
        when(currentUser.getId()).thenReturn(userId);
        when(obligationRepository.findObligationModelByNameContainingIgnoreCaseAndUserId(any(String.class), any(PageRequest.class), any(Long.class))).thenReturn(resultList);

        Assert.assertTrue(CollectionUtils.isNotEmpty(underTest.search(searchQuery, currentUser)));
        verify(searchQuery).getPage();
        verify(searchQuery).getLimit();
        verify(searchQuery).getSortField();
        verify(searchQuery).getFreeText();
        verify(searchQuery).isDesc();
        verifyNoMoreInteractions(searchQuery);
        verify(currentUser).getId();
        verifyNoMoreInteractions(currentUser);
        verify(obligationRepository).findObligationModelByNameContainingIgnoreCaseAndUserId(any(String.class), any(PageRequest.class), any(Long.class));
        verifyNoMoreInteractions(obligationRepository);
    }

    @Override
    protected Class<ObligationModel> getGenericClassOfService() {
        return ObligationModel.class;
    }

    @Override
    protected AbstractModelService<ObligationModel> getGenericModelService() {
        return underTest;
    }

    @Override
    protected JpaRepository<ObligationModel, Long> getModelRepository() {
        return obligationRepository;
    }
}
