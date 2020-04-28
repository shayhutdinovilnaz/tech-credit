package com.technical.credit.obligationservice.service.impl;


import com.technical.credit.obligationservice.model.LanguageModel;
import org.junit.Assert;
import org.junit.Test;


public class DefaultSessionServiceTest {

    private final DefaultSessionService underTest = new DefaultSessionService();

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