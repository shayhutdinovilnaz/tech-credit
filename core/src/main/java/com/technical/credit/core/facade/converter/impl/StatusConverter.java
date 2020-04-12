package com.technical.credit.core.facade.converter.impl;

import com.technical.credit.core.data.StatusData;
import com.technical.credit.core.facade.converter.Converter;
import com.technical.credit.core.model.StatusModel;
import com.technical.credit.core.service.SessionService;
import com.technical.credit.core.wrapper.LocalizationFunctionWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusConverter implements Converter<StatusData, StatusModel> {
    private final SessionService sessionService;
    private final LocalizationFunctionWrapper localizationFunctionWrapper;

    @Override
    public StatusData convert(final StatusModel source) {
        final StatusData target = new StatusData();
        target.setId(source.getId());
        target.setName(localizationFunctionWrapper.getLocalizedStringValue(sessionService.getCurrentLanguage(), source.getName()));
        return target;
    }

    @Override
    public StatusModel reverseConvert(final StatusData source) {
        final StatusModel target = new StatusModel();
        target.setId(source.getId());
        target.setName(localizationFunctionWrapper.updateLocalizedValue(sessionService.getCurrentLanguage(), source.getName(), target.getName()));
        return target;
    }
}
