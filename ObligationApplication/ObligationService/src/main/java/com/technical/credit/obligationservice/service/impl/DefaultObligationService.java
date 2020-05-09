package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.model.UserModel;
import com.technical.credit.obligationservice.repository.ObligationRepository;
import com.technical.credit.obligationservice.service.ObligationService;
import com.technical.credit.obligationservice.service.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultObligationService extends AbstractModelService<ObligationModel> implements ObligationService {
    private final ObligationRepository obligationRepository;

    @Override
    public List<ObligationModel> search(final SearchQuery searchQuery, final UserModel user) {
        Assert.notNull(searchQuery, "SearchQuery domain class must not be null!");
        Assert.notNull(user, "User domain class must not be null!");

        return obligationRepository.findObligationModelByNameContainingIgnoreCaseAndUserId(getQueryText(searchQuery.getFreeText()), buildPageRequest(searchQuery), user.getId());
    }

    @Override
    protected JpaRepository<ObligationModel, Long> getItemRepository() {
        return obligationRepository;
    }
}
