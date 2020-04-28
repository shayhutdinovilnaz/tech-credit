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

    private String getQueryText(final String freeText) {
        return Optional.ofNullable(freeText).map(String::trim).orElse(StringUtils.EMPTY);
    }

    private Pageable buildPageRequest(final SearchQuery searchQuery) {
        final Sort sort = Sort.by(searchQuery.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, searchQuery.getSortField());
        return PageRequest.of(searchQuery.getPage(), searchQuery.getLimit(), sort);
    }

    @Override
    protected JpaRepository<ObligationModel, Long> getItemRepository() {
        return obligationRepository;
    }
}
