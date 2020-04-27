package com.technical.credit.obligationservice.service;


import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.UserModel;

public interface SessionService {
    /**
     * The retrieving the current language of the session.
     *
     * @return the current session language.
     */
    LanguageModel getCurrentLanguage();

    /**
     * The retrieving the current user of the session.
     *
     * @return the current session user.
     */
    UserModel getCurrentUser();

}
