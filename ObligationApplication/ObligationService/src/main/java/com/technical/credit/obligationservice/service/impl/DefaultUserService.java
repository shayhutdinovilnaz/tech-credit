package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {
    @Override
    public UserModel getById(long id) {
        return null;
    }
}
