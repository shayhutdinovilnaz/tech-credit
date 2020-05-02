package account.facade.converter.impl;

import account.facade.converter.Converter;
import account.facade.data.UserData;
import account.model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserConverter implements Converter<UserData, UserModel> {
    @Override
    public UserData convert(final UserModel source) {
        Assert.notNull(source, "Domain model could not be nullable.");

        final UserData target = new UserData();
        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setEmail(source.getEmail());
        return target;
    }
}
