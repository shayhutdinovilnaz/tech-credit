package account.facade.impl;

import account.facade.UserFacade;
import account.facade.data.UserData;
import account.model.UserModel;
import account.service.UserService;
import com.technical.credit.common.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class DefaultUserFacade implements UserFacade {
    private final UserService userService;
    private final Converter<UserData, UserModel> userConverter;

    @Override
    public UserData getByUsername(final String username) {
        Assert.notNull(username, "Username could not be nullable.");

        return userConverter.convert(userService.getByUsername(username));
    }

    @Override
    public UserData getById(long id) {
        return userConverter.convert(userService.getById(id));
    }
}
