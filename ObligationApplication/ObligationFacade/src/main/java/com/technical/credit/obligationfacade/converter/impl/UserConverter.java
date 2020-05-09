package com.technical.credit.obligationfacade.converter.impl;

import com.technical.credit.obligationfacade.converter.Converter;
import com.technical.credit.obligationfacade.data.UserData;
import com.technical.credit.obligationservice.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.SessionService;
import com.technical.credit.obligationservice.service.impl.DefaultDisplayNameService;
import com.technical.credit.obligationservice.utils.YAMLConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserConverter implements Converter<UserData, UserModel> {
    private final GenericInstanceFactory genericInstanceFactory;

    private final YAMLConfig yamlConfig;
    private final DefaultDisplayNameService defaultDisplayNameService;
    private final SessionService sessionService;

    @Override
    public UserData convert(final UserModel source) {
        final UserData target = genericInstanceFactory.getInstance(UserData.class);
        target.setId(source.getId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        return target;

        Assert.notNull(source, "The UserModel instance of converting can't be nullable.");

        final UserData userData = new UserData();
        String fName = source.getFirstName();
        String lName = source.getLastName();
        String mName = source.getMiddleName();
        userData.setFirstName(fName);
        userData.setLastName(lName);
        userData.setMiddleName(mName);

        Map<String, String> patterns = yamlConfig.getPatterns();
        Map<String, String> isoCodes = yamlConfig.getIsocodes();

        Set<ObligationModel> obligationModel = source.getObligations();
        Assert.notNull(obligationModel, "obligationModel must not be null");
        if (obligationModel.isEmpty()) {
            userData.setDisplayName(patterns.get("default"));
            return userData;
        }

        String isoCode = sessionService.getCurrentLanguage().getIsoCode();
        if (Objects.nonNull(isoCode)) {
            if (isoCodes.containsKey(isoCode)) {
                String language = isoCodes.get(isoCode);
                String displayName = defaultDisplayNameService.createDisplayName(fName, lName, patterns.get(language));
                userData.setDisplayName(displayName);
            } else {
                userData.setDisplayName(patterns.get("default"));
                return userData;
            }
        } else {
            userData.setDisplayName(patterns.get("default"));
        }
        return userData;
    }

    @Override
    public UserModel reverseConvert(final UserData source) {
        throw new UnsupportedOperationException("Method is not supported for user data.");
    }
}
