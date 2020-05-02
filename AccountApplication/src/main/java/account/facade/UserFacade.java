package account.facade;

import account.facade.data.UserData;

public interface UserFacade {
    UserData getByUsername(String username);

    UserData getById(long id);
}
