package account.facade;

import account.facade.data.UserData;

public interface UserFacade {
    /**
     * Retrieve user by username.
     *
     * @param username -The required username.
     * @return required user.
     * @throws com.technical.credit.core.exception.ModelNotFoundException - if user not found by username
     */
    UserData getByUsername(String username);

    /**
     * Retrieve user by id.
     *
     * @param id -The required id.
     * @return required user.
     * @throws com.technical.credit.core.exception.ModelNotFoundException - if user not found by id
     */
    UserData getById(long id);
}
