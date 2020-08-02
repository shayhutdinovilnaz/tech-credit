package account.service;

import account.model.UserModel;

public interface UserService {
    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param user must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     */
    UserModel save(UserModel user);

    /**
     * Retrieves an entity by its username.
     *
     * @param username must not be {@literal null}.
     * @return the entity with the given username if none found.
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     */
    UserModel getByUsername(String username);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    UserModel getById(long id);

    /**
     * Retrieves an entity by its email.
     *
     * @param email must not be {@literal null}.
     * @return the entity with the given email if none found.
     * @throws IllegalArgumentException if {@literal email} is {@literal null}.
     */
    UserModel getByEmail(String email);


    /**
     * Checks existing user with username or not.
     *
     * @param username must not be {@literal null}.
     * @return if exist user with required username - true, else -false
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     */
    boolean isExistUserWithUsername(String username);

    /**
     * Checks existing user with email or not.
     *
     * @param email must not be {@literal null}.
     * @return if exist user with required email - true, else -false
     * @throws IllegalArgumentException if {@literal email} is {@literal null}.
     */
    boolean isExistUserWithEmail(String email);
}
