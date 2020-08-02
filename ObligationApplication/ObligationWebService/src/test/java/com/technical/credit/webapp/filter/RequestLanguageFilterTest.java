package com.technical.credit.webapp.filter;

import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.service.LanguageService;
import com.technical.credit.obligationservice.service.RequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RequestLanguageFilterTest {

    @InjectMocks
    private RequestLanguageFilter underTest;

    @Mock
    private LanguageService languageService;

    @Mock
    private RequestService requestService;

    @Test
    public void testFilter() throws IOException, ServletException {
        final LanguageModel ruLanguage = mock(LanguageModel.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final ServletResponse response = mock(ServletResponse.class);
        final FilterChain filterChain = mock(FilterChain.class);
        final String langParam = "language";
        final String ruIsoCode = "ru";

        when(request.getHeader(langParam)).thenReturn(ruIsoCode);
        when(languageService.getByIsoCode(ruIsoCode)).thenReturn(ruLanguage);

        underTest.doFilter(request, response, filterChain);
        verify(request).getHeader(langParam);
        verifyNoMoreInteractions(request);
        verify(languageService).getByIsoCode(ruIsoCode);
        verifyNoMoreInteractions(languageService);
        verify(requestService).setCurrentLanguage(ruLanguage);
        verifyNoMoreInteractions(requestService);
        verifyNoMoreInteractions(ruLanguage);
        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(response);
    }

}