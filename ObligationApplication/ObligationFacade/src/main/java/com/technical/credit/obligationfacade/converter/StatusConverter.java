package com.technical.credit.obligationfacade.converter;

import com.technical.credit.common.converter.Converter;
import com.technical.credit.obligationfacade.data.StatusData;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.StatusModel;
import com.technical.credit.obligationservice.service.LocalizationService;
import com.technical.credit.obligationservice.service.RequestService;
import com.technical.credit.obligationservice.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class StatusConverter implements Converter<StatusData, StatusModel> {
    private final GenericInstanceFactory genericInstanceFactory;
    private final RequestService requestService;
    private final LocalizationService localizationService;
    private final StatusService statusService;

    @Override
    public StatusData convert(final StatusModel source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final StatusData target = genericInstanceFactory.getInstance(StatusData.class);
        target.setId(source.getId());
        target.setName(localizationService.getLocalizedStringValue(requestService.getCurrentLanguage(), source.getName()));
        return target;
    }

    @Override
    public StatusModel reverseConvert(final StatusData source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final StatusModel target = genericInstanceFactory.getInstance(StatusModel.class);
        target.setId(source.getId());
        attachInternalLocalizedAttributes(target);
        target.setName(localizationService.addLocalizedStringValue(requestService.getCurrentLanguage(), source.getName(), target.getName()));
        return target;
    }

    private void attachInternalLocalizedAttributes(final StatusModel externalStatus) {
        final StatusModel internalStatus = statusService.getById(externalStatus.getId());
        externalStatus.setName(internalStatus.getName());
    }
}
