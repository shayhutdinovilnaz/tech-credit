package com.technical.credit.obligationservice.service.impl;


import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class DefaultRequestServiceTest {

    @InjectMocks
    private DefaultRequestService underTest;

    @Mock
    private UserService userService;

    @Test
    public void testCurrentLanguage() {
        final LanguageModel language = new LanguageModel();
        underTest.setCurrentLanguage(language);
        Assert.assertEquals(language, underTest.getCurrentLanguage());
    }

    @Test
    public void testCurrentUser() {
        //TODO
    }
}