package com.technical.credit.obligationfacade.converter.impl;


import com.technical.credit.core.converter.Converter;
import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.data.SkillData;
import com.technical.credit.obligationfacade.data.StatusData;
import com.technical.credit.obligationfacade.data.UserData;
import com.technical.credit.core.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.model.SkillModel;
import com.technical.credit.obligationservice.model.StatusModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObligationConverter implements Converter<ObligationData, ObligationModel> {
    private final GenericInstanceFactory genericInstanceFactory;
    private final Converter<SkillData, SkillModel> skillConverter;
    private final Converter<StatusData, StatusModel> statusConverter;
    private final Converter<UserData, UserModel> userConverter;
    private final UserService userService;

    @Override
    public ObligationData convert(final ObligationModel source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final ObligationData target = genericInstanceFactory.getInstance(ObligationData.class);
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setExpiredDate(source.getExpiredDate());
        target.setUser(Optional.ofNullable(source.getUserId()).map(userService::getById).map(userConverter::convert).orElse(null));
        target.setSkill(Optional.ofNullable(source.getSkill()).map(skillConverter::convert).orElse(null));
        target.setStatus(Optional.ofNullable(source.getStatus()).map(statusConverter::convert).orElse(null));
        return target;
    }

    @Override
    public ObligationModel reverseConvert(final ObligationData source) {
        Assert.notNull(source, "The source instance of converting can't be nullable.");

        final ObligationModel target = genericInstanceFactory.getInstance(ObligationModel.class);
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setExpiredDate(source.getExpiredDate());
        target.setUserId(Optional.ofNullable(source.getUser()).map(UserData::getId).map(userService::getById).map(UserModel::getId).orElse(null));
        target.setSkill(Optional.ofNullable(source.getSkill()).map(skillConverter::reverseConvert).orElse(null));
        target.setStatus(Optional.ofNullable(source.getStatus()).map(statusConverter::reverseConvert).orElse(null));
        return target;
    }
}
