package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.repository.ObligationRepository;
import com.technical.credit.obligationservice.service.ObligationService;
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
