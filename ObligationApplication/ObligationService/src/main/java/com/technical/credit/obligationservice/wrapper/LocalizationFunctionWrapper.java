package com.technical.credit.obligationservice.wrapper;

import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.LocalizedStringModel;
import com.technical.credit.obligationservice.util.LocalizationFunction;
import org.springframework.stereotype.Service;

@Service
public class LocalizationFunctionWrapper {
    public String getLocalizedStringValue(final LanguageModel language, final LocalizedStringModel localizedString) {
        return LocalizationFunction.getLocalizedStringValue(language, localizedString);
    }

    public LocalizedStringModel updateLocalizedValue(final LanguageModel language, final String value, final LocalizedStringModel localizedString) {
        return LocalizationFunction.updateLocalizedValue(language, value, localizedString);
    }
}
