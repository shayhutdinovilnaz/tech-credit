package com.technical.credit.obligationservice.service;


import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.UserModel;

public interface RequestService {
    /**
     * The retrieving the current language of the request.
     *
     * @return the current request language.
     */
    LanguageModel getCurrentLanguage();


    /**
     * The session language is set up.
     *
     * @param language - the request language
     */
    void setCurrentLanguage(LanguageModel language);

    /**
     * The retrieving the current user of the request.
     *
     * @return the current request user.
     */
    UserModel getCurrentUser();

}
