package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.SkillModel;
import com.technical.credit.obligationservice.repository.SkillRepository;
import com.technical.credit.obligationservice.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultSkillService extends AbstractModelService<SkillModel> implements SkillService {
    private final SkillRepository skillRepository;

    @Override
    protected JpaRepository<SkillModel, Long> getItemRepository() {
        return skillRepository;
    }
}
