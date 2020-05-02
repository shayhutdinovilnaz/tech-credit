package account.service;

import account.model.UserModel;

public interface UserService {
    UserModel getByUsername(String username);

    UserModel getById(long id);
}
