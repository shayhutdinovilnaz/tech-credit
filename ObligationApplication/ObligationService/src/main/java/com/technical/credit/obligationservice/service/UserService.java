package com.technical.credit.obligationservice.service;

import com.technical.credit.obligationservice.model.UserModel;

public interface UserService {
    /**
     * The retrieving user by id.
     *
     * @param id the id of user
     * @return the found user
     */
    UserModel getById(long id);
}
