package account.facade.converter;

import account.facade.data.UserData;
import account.model.UserModel;
import com.technical.credit.core.converter.Converter;
import com.technical.credit.core.factory.GenericInstanceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class UserConverter implements Converter<UserData, UserModel> {
    private final GenericInstanceFactory genericInstanceFactory;

    @Override
    public UserData convert(final UserModel source) {
        Assert.notNull(source, "Domain model could not be nullable.");

        final UserData target = genericInstanceFactory.getInstance(UserData.class);
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
