package account.service;

import account.model.RoleModel;

public interface RoleService {

    /**
     * Retrieves an entity by its name.
     *
     * @param name must not be {@literal null}.
     * @return the entity with the given name if none found.
     * @throws IllegalArgumentException if {@literal name} is {@literal null}.
     */
    RoleModel getRoleByName(String name);
}
