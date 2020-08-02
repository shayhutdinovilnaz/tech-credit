package com.technical.credit.obligationfacade.converter;

import com.technical.credit.core.converter.Converter;
import com.technical.credit.obligationfacade.data.SkillData;
import com.technical.credit.core.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.SkillModel;
import com.technical.credit.obligationservice.service.LocalizationService;
import com.technical.credit.obligationservice.service.RequestService;
import com.technical.credit.obligationservice.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class SkillConverter implements Converter<SkillData, SkillModel> {
    private final GenericInstanceFactory genericInstanceFactory;
    private final RequestService requestService;
    private final LocalizationService localizationService;
    private final SkillService skillService;

    @Override
    public SkillData convert(final SkillModel source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final SkillData target = genericInstanceFactory.getInstance(SkillData.class);
        target.setId(source.getId());
        target.setName(localizationService.getLocalizedStringValue(requestService.getCurrentLanguage(), source.getName()));
        return target;
    }

    @Override
    public SkillModel reverseConvert(final SkillData source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final SkillModel target = genericInstanceFactory.getInstance(SkillModel.class);
        target.setId(source.getId());
        attachInternalLocalizedAttributes(target);
        target.setName(localizationService.addLocalizedStringValue(requestService.getCurrentLanguage(), source.getName(), target.getName()));
        return target;
    }

    private void attachInternalLocalizedAttributes(final SkillModel externalSkill) {
        final SkillModel internalSkill = skillService.getById(externalSkill.getId());
        externalSkill.setName(internalSkill.getName());
    }
}
