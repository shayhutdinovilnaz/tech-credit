package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.StatusModel;
import com.technical.credit.obligationservice.repository.StatusRepository;
import com.technical.credit.obligationservice.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultStatusService extends AbstractModelService<StatusModel> implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    protected JpaRepository<StatusModel, Long> getItemRepository() {
        return statusRepository;
    }
}
