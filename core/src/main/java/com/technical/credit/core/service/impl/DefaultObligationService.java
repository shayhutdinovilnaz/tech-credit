package com.technical.credit.core.service.impl;

import com.technical.credit.core.model.ObligationModel;
import com.technical.credit.core.repository.ObligationRepository;
import com.technical.credit.core.service.ObligationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultObligationService extends AbstractModelService<ObligationModel> implements ObligationService {
    private final ObligationRepository obligationRepository;

    @Override
    protected JpaRepository<ObligationModel, Long> getItemRepository() {
        return obligationRepository;
    }
}
