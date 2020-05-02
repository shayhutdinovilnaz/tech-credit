package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.RequestService;
import com.technical.credit.obligationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequiredArgsConstructor
@RequestScope
public class DefaultRequestService implements RequestService {
    private final UserService userService;
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
        if (currentUser == null) {
            currentUser = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return currentUser;
    }
}
