package com.technical.credit.obligationfacade.converter.impl;

import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.UserData;
import com.technical.credit.obligationservice.model.UserModel;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserConverter implements Converter<UserData, UserModel> {
    @Override
    public UserData convert(UserModel source) {
        return null;
    }

    @Override
    public UserModel reverseConvert(UserData source) {
        return null;
    }
}
