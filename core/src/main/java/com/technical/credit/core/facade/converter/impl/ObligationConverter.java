package com.technical.credit.core.facade.converter.impl;

import com.technical.credit.core.data.ObligationData;
import com.technical.credit.core.data.SkillData;
import com.technical.credit.core.data.StatusData;
import com.technical.credit.core.facade.converter.Converter;
import com.technical.credit.core.model.ObligationModel;
import com.technical.credit.core.model.SkillModel;
import com.technical.credit.core.model.StatusModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObligationConverter implements Converter<ObligationData, ObligationModel> {
    private final Converter<SkillData, SkillModel> skillConverter;
    private final Converter<StatusData, StatusModel> statusConverter;

    @Override
    public ObligationData convert(final ObligationModel source) {
        final ObligationData target = new ObligationData();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setExpiredDate(source.getExpiredDate());
        //todo user
        target.setUserData(null);
        target.setSkill(skillConverter.convert(source.getSkill()));
        target.setStatus(statusConverter.convert(source.getStatus()));
        return target;
    }

    @Override
    public ObligationModel reverseConvert(final ObligationData source) {
        final ObligationModel target = new ObligationModel();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setExpiredDate(source.getExpiredDate());
        //todo user
        target.setUserId(null);
        target.setSkill(skillConverter.reverseConvert(source.getSkill()));
        target.setStatus(statusConverter.reverseConvert(source.getStatus()));
        return target;
    }
}
