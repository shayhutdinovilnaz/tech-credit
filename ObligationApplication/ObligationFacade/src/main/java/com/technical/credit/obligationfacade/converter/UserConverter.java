package com.technical.credit.obligationfacade.converter;

import com.technical.credit.core.converter.Converter;
import com.technical.credit.obligationfacade.data.UserData;
import com.technical.credit.core.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserConverter implements Converter<UserData, UserModel> {
    private final GenericInstanceFactory genericInstanceFactory;

    @Override
    public UserData convert(final UserModel source) {
        final UserData target = genericInstanceFactory.getInstance(UserData.class);
        target.setId(source.getId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        return target;
    }

    @Override
    public UserModel reverseConvert(final UserData source) {
        throw new UnsupportedOperationException("Method is not supported for user data.");
    }
}
