package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.SessionService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("session")
public class DefaultSessionService implements SessionService {
    private LanguageModel currentLanguage;
    private UserModel currentUser;

    @Override
    public LanguageModel getCurrentLanguage() {
        return currentLanguage;
    }

    @Override
    public void setCurrentLanguage(final LanguageModel language) {
        this.currentLanguage = language;
    }

    @Override
    public UserModel getCurrentUser() {
        return currentUser;
    }
}
