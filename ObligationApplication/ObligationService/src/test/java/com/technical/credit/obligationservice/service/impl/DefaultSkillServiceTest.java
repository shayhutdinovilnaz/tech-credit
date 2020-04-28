package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.SkillModel;
import com.technical.credit.obligationservice.repository.SkillRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

public class DefaultSkillServiceTest extends AbstractModelServiceTest<SkillModel> {

    @InjectMocks
    private DefaultSkillService skillService;

    @Mock
    private SkillRepository skillRepository;

    @Override
    protected Class<SkillModel> getGenericClassOfService() {
        return SkillModel.class;
    }

    @Override
    protected AbstractModelService<SkillModel> getGenericModelService() {
        return skillService;
    }

    @Override
    protected JpaRepository<SkillModel, Long> getModelRepository() {
        return skillRepository;
    }
}
