package com.technical.credit.obligationservice.service.impl;


import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class DefaultUserServiceTest extends AbstractModelServiceTest<UserModel> {

    @InjectMocks
    private DefaultUserService userService;

    @Mock
    private UserRepository userRepository;

    @Override
    protected Class<UserModel> getGenericClassOfService() {
        return UserModel.class;
    }

    @Override
    protected AbstractModelService<UserModel> getGenericModelService() {
        return userService;
    }

    @Override
    protected JpaRepository<UserModel, Long> getModelRepository() {
        return userRepository;
    }
}