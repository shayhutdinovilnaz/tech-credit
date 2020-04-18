package com.technical.credit.obligationfacade.converter.impl;

import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.StatusData;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.StatusModel;
import com.technical.credit.obligationservice.service.LocalizationService;
import com.technical.credit.obligationservice.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusConverter implements Converter<StatusData, StatusModel> {
    private final GenericInstanceFactory genericInstanceFactory;
    private final SessionService sessionService;
    private final LocalizationService localizationService;

    @Override
    public StatusData convert(final StatusModel source) {
        final StatusData target = genericInstanceFactory.getInstance(StatusData.class);
        target.setId(source.getId());
        target.setName(localizationService.getLocalizedStringValue(sessionService.getCurrentLanguage(), source.getName()));
        return target;
    }

    @Override
    public StatusModel reverseConvert(final StatusData source) {
        final StatusModel target = genericInstanceFactory.getInstance(StatusModel.class);
        target.setId(source.getId());
        target.setName(localizationService.addLocalizedStringValue(sessionService.getCurrentLanguage(), source.getName(), target.getName()));
        return target;
    }
}
