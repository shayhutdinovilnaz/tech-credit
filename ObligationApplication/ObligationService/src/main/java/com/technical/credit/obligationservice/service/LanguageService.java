package com.technical.credit.obligationservice.service;

import com.technical.credit.obligationservice.model.LanguageModel;

public interface LanguageService extends ModelService<LanguageModel> {
    /**
     * Retrieve the language model by ISO-code.
     *
     * @param isoCode - ISO-code of language
     * @return language model
     */
    LanguageModel getByIsoCode(String isoCode);
}
