package account.facade.converter;

import account.facade.data.UserData;
import account.model.UserModel;
import com.technical.credit.common.converter.Converter;
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

    @Override
    public UserModel reverseConvert(UserData source) {
        throw new UnsupportedOperationException("Method is not supported.");
    }
}
