package com.technical.credit.core.facade.converter.impl;

import com.technical.credit.core.data.SkillData;
import com.technical.credit.core.facade.converter.Converter;
import com.technical.credit.core.model.SkillModel;
import com.technical.credit.core.service.SessionService;
import com.technical.credit.core.wrapper.LocalizationFunctionWrapper;
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
