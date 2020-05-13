package com.technical.credit.obligationservice.service.impl;


import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.security.AuthenticationDetailsProvider;
import com.technical.credit.obligationservice.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DefaultRequestServiceTest {

    @InjectMocks
    private DefaultRequestService underTest;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationDetailsProvider authenticationDetailsProvider;

    @Test
    public void testCurrentLanguage() {
        final LanguageModel language = new LanguageModel();
        underTest.setCurrentLanguage(language);
        Assert.assertEquals(language, underTest.getCurrentLanguage());
    }

    @Test
    public void testCurrentUser() {
        final String username = "John";
        final UserModel currentUser = mock(UserModel.class);

        when(authenticationDetailsProvider.getAuthenticatedPrincipalUsername()).thenReturn(username);
        when(userService.getByUsername(username)).thenReturn(currentUser);

        Assert.assertNotNull(underTest.getCurrentUser());
        verify(authenticationDetailsProvider).getAuthenticatedPrincipalUsername();
        verifyNoMoreInteractions(authenticationDetailsProvider);
        verify(userService).getByUsername(username);
        verifyNoMoreInteractions(userService);
        verifyZeroInteractions(currentUser);
    }

    @Test
    public void testCurrentUserRepeatRetiring() {
        final String username = "John";
        final UserModel currentUser = mock(UserModel.class);

        when(authenticationDetailsProvider.getAuthenticatedPrincipalUsername()).thenReturn(username);
        when(userService.getByUsername(username)).thenReturn(currentUser);

        Assert.assertNotNull(underTest.getCurrentUser());
        verify(authenticationDetailsProvider).getAuthenticatedPrincipalUsername();
        verifyNoMoreInteractions(authenticationDetailsProvider);
        verify(userService).getByUsername(username);
        verifyNoMoreInteractions(userService);
        verifyZeroInteractions(currentUser);

        Assert.assertNotNull(underTest.getCurrentUser());
        verifyZeroInteractions(currentUser);
        verifyZeroInteractions(userService);
        verifyZeroInteractions(authenticationDetailsProvider);
    }
}