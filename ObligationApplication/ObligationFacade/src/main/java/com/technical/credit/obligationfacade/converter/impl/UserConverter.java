package com.technical.credit.obligationfacade.converter.impl;

import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.UserData;
import com.technical.credit.obligationservice.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserConverter implements Converter<UserData, UserModel> {
    @Override
    public UserData
    convert(final UserModel source) {
        //TODO ADD INVOKE A SERVICE FOR GENERATION DISPLAY NAME
        return null;
    }

    @Override
    public UserModel reverseConvert(final UserData source) {
        return null;
    }
}
