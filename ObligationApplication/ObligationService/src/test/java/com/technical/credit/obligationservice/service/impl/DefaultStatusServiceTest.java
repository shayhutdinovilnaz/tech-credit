package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.StatusModel;
import com.technical.credit.obligationservice.repository.StatusRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class DefaultStatusServiceTest extends AbstractModelServiceTest<StatusModel> {
    @InjectMocks
    private DefaultStatusService statusService;

    @Mock
    private StatusRepository statusRepository;

    @Override
    protected Class<StatusModel> getGenericClassOfService() {
        return StatusModel.class;
    }

    @Override
    protected AbstractModelService<StatusModel> getGenericModelService() {
        return statusService;
    }

    @Override
    protected JpaRepository<StatusModel, Long> getModelRepository() {
        return statusRepository;
    }
}