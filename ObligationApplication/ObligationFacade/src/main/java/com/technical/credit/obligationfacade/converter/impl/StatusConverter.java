package com.technical.credit.obligationfacade.converter.impl;

import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.StatusData;
import com.technical.credit.obligationservice.model.StatusModel;
import com.technical.credit.obligationservice.service.SessionService;
import com.technical.credit.obligationservice.wrapper.LocalizationFunctionWrapper;
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
