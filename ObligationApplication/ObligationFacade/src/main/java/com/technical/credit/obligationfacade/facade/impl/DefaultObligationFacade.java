package com.technical.credit.obligationfacade.facade.impl;


import com.technical.credit.core.converter.Converter;
import com.technical.credit.obligationfacade.data.ObligationData;
import com.technical.credit.obligationfacade.facade.ObligationFacade;
import com.technical.credit.core.factory.GenericInstanceFactory;
import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.service.CategoryService;
import com.technical.credit.obligationservice.service.ObligationService;
import com.technical.credit.obligationservice.service.RequestService;
import com.technical.credit.obligationservice.service.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultObligationFacade implements ObligationFacade {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultObligationFacade.class);

    private final Converter<ObligationData, ObligationModel> obligationConverter;
    private final ObligationService obligationService;
    private final RequestService requestService;
    private final CategoryService categoryService;
    private final GenericInstanceFactory genericInstanceFactory;

    @Override
    public ObligationData save(final ObligationData obligationData) {
        Assert.notNull(obligationData, "Domain class must not be null!");

        final ObligationModel obligationModel = obligationConverter.reverseConvert(obligationData);
        if (Objects.isNull(obligationModel.getUserId())) {
            obligationModel.setUserId(requestService.getCurrentUser().getId());
        }

        obligationService.save(obligationModel);
        return obligationConverter.convert(obligationModel);
    }

    @Override
    public ObligationData search(final long id) {
        return obligationConverter.convert(obligationService.getById(id));
    }

    @Override
    public List<ObligationData> search(final String freeText, final String sortField, final boolean desc, final int page, final int limit) {
        final SearchQuery searchQuery = genericInstanceFactory.getInstance(SearchQuery.class);
        searchQuery.setFreeText(freeText);
        searchQuery.setDesc(desc);
        searchQuery.setSortField(sortField);
        searchQuery.setPage(page);
        searchQuery.setLimit(limit);

        return Optional.ofNullable(obligationService.search(searchQuery, requestService.getCurrentUser()))
                .orElseGet(Collections::emptyList)
                .stream()
                .map(obligationConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(final long id) {
        obligationService.delete(obligationService.getById(id));
    }

    @Override
    public void assignToCategory(long obligationID, long categoryID) {

        final ObligationModel obligation = obligationService.getById(obligationID);
        final CategoryModel category = categoryService.getById(categoryID);
        obligationService.addToCategory(obligation, category);

        LOG.debug("Obligation is added to category. Obligation id: {}, category id: {}", obligationID, categoryID);
    }
}
