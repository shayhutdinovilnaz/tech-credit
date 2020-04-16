package com.technical.credit.obligationfacade.converter.impl;

import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.SkillData;
import com.technical.credit.obligationservice.model.SkillModel;
import com.technical.credit.obligationservice.service.SessionService;
import com.technical.credit.obligationservice.wrapper.LocalizationFunctionWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillConverter implements Converter<SkillData, SkillModel> {
    private final SessionService sessionService;
    private final LocalizationFunctionWrapper localizationFunctionWrapper;

    @Override
    public SkillData convert(final SkillModel source) {
        final SkillData target = new SkillData();
        target.setId(source.getId());
        target.setName(localizationFunctionWrapper.getLocalizedStringValue(sessionService.getCurrentLanguage(), source.getName()));
        return target;
    }

    @Override
    public SkillModel reverseConvert(final SkillData source) {
        final SkillModel target = new SkillModel();
        target.setId(source.getId());
        target.setName(localizationFunctionWrapper.updateLocalizedValue(sessionService.getCurrentLanguage(), source.getName(), target.getName()));
        return target;
    }
}
