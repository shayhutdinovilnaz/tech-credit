package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.repository.ObligationRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class DefaultObligationServiceTest extends AbstractModelServiceTest<ObligationModel> {

    @InjectMocks
    private DefaultObligationService obligationService;

    @Mock
    private ObligationRepository obligationRepository;

    @Override
    protected Class<ObligationModel> getGenericClassOfService() {
        return ObligationModel.class;
    }

    @Override
    protected AbstractModelService<ObligationModel> getGenericModelService() {
        return obligationService;
    }

    @Override
    protected JpaRepository<ObligationModel, Long> getModelRepository() {
        return obligationRepository;
    }
}
