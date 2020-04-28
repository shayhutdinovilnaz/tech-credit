package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.SessionService;
import org.springframework.stereotype.Service;

@Service
public class DefaultSessionService implements SessionService {
    @Override
    public LanguageModel getCurrentLanguage() {
        return null;
    }

    @Override
    public UserModel getCurrentUser() {
        return null;
    }
}
