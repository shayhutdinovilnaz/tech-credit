package com.technical.credit.core.wrapper;

import com.technical.credit.core.model.LanguageModel;
import com.technical.credit.core.model.LocalizedStringModel;
import com.technical.credit.core.util.LocalizationFunction;
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
