package com.technical.credit.obligationfacade.converter.impl;

import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.SkillData;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.SkillModel;
import com.technical.credit.obligationservice.service.LocalizationService;
import com.technical.credit.obligationservice.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillConverter implements Converter<SkillData, SkillModel> {
    private final GenericInstanceFactory genericInstanceFactory;
    private final SessionService sessionService;
    private final LocalizationService localizationService;

    @Override
    public SkillData convert(final SkillModel source) {
        final SkillData target = genericInstanceFactory.getInstance(SkillData.class);
        target.setId(source.getId());
        target.setName(localizationService.getLocalizedStringValue(sessionService.getCurrentLanguage(), source.getName()));
        return target;
    }

    @Override
    public SkillModel reverseConvert(final SkillData source) {
        final SkillModel target = genericInstanceFactory.getInstance(SkillModel.class);
        target.setId(source.getId());
        target.setName(localizationService.addLocalizedStringValue(sessionService.getCurrentLanguage(), source.getName(), target.getName()));
        return target;
    }
}
