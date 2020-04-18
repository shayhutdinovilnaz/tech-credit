package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.model.LocalizedStringModel;
import com.technical.credit.obligationservice.model.LocalizedStringValueModel;
import com.technical.credit.obligationservice.service.LocalizationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DefaultLocalizationService implements LocalizationService {
    private final GenericInstanceFactory genericInstanceFactory;

    public String getLocalizedStringValue(final LanguageModel language, final LocalizedStringModel localizedString) {
        return Optional.ofNullable(localizedString.getLocalizedValues())
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .filter(value -> value.getLanguage().equals(language))
                .findAny()
                .map(LocalizedStringValueModel::getValue)
                .orElseGet(localizedString::getDefaultValue);
    }

    public LocalizedStringModel addLocalizedStringValue(final LanguageModel language, final String value, final LocalizedStringModel localizedString) {
        final LocalizedStringModel updatedLocalizedString = Optional.ofNullable(localizedString).orElseGet(() -> genericInstanceFactory.getInstance(LocalizedStringModel.class));
        if (language != null) {
            final Collection<LocalizedStringValueModel> localizedValues = CollectionUtils.emptyIfNull(updatedLocalizedString.getLocalizedValues());
            final LocalizedStringValueModel localized = localizedValues
                    .stream()
                    .filter(localizedValue -> localizedValue.getLanguage().equals(language))
                    .findAny().orElseGet(() -> createLocalizedString(language, value));
            localizedValues.add(localized);
        } else {
            updatedLocalizedString.setDefaultValue(value);
        }

        return updatedLocalizedString;
    }

    private static LocalizedStringValueModel createLocalizedString(final LanguageModel language, final String value) {
        final LocalizedStringValueModel localizedStringValue = new LocalizedStringValueModel();
        localizedStringValue.setLanguage(language);
        localizedStringValue.setValue(value);
        return localizedStringValue;
    }
}
