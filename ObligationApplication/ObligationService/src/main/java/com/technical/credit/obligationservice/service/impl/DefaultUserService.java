package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.repository.UserRepository;
import com.technical.credit.obligationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService extends AbstractModelService<UserModel> implements UserService {
    private final UserRepository userRepository;

    @Override
    protected JpaRepository<UserModel, Long> getItemRepository() {
        return userRepository;
    }
}
