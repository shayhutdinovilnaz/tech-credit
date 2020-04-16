package com.technical.credit.obligationservice.util;

import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.LocalizedStringModel;
import com.technical.credit.obligationservice.model.LocalizedStringValueModel;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class LocalizationFunction {
    private LocalizationFunction() {
        throw new IllegalStateException("Localiztion functions can't have instance.");
    }

    public static String getLocalizedStringValue(final LanguageModel language, final LocalizedStringModel localizedString) {
        return Optional.ofNullable(localizedString.getLocalizedValues())
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .filter(value -> value.getLanguage().equals(language))
                .findAny()
                .map(LocalizedStringValueModel::getValue)
                .orElseGet(localizedString::getDefaultValue);
    }

    public static LocalizedStringModel updateLocalizedValue(final LanguageModel language, final String value, final LocalizedStringModel localizedString) {
        final LocalizedStringModel updatedLocalizedString = Optional.ofNullable(localizedString).orElseGet(LocalizedStringModel::new);
        final Collection<LocalizedStringValueModel> localizedValues = CollectionUtils.emptyIfNull(updatedLocalizedString.getLocalizedValues());
        final LocalizedStringValueModel localized = localizedValues
                .stream()
                .filter(localizedValue -> localizedValue.getLanguage().equals(language))
                .findAny().orElseGet(() -> createLocalizedString(language, value));
        localizedValues.add(localized);
        return updatedLocalizedString;
    }

    private static LocalizedStringValueModel createLocalizedString(final LanguageModel language, final String value) {
        final LocalizedStringValueModel localizedStringValue = new LocalizedStringValueModel();
        localizedStringValue.setLanguage(language);
        localizedStringValue.setValue(value);
        return localizedStringValue;
    }
}
