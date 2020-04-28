package com.technical.credit.obligationservice.service;

import com.technical.credit.obligationservice.model.LanguageModel;

public interface LanguageService extends ModelService<LanguageModel> {
    /**
     * @param isoCode
     * @return
     */
    LanguageModel getByIsoCode(String isoCode);
}
