package com.technical.credit.obligationservice.service.impl;


import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import com.technical.credit.obligationservice.model.ItemModel;
import com.technical.credit.obligationservice.service.ModelService;
import com.technical.credit.obligationservice.service.SearchQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Optional;

public abstract class AbstractModelService<T extends ItemModel> implements ModelService<T> {
    @Override
    @Transactional
    public void save(final T item) {
        Assert.notNull(item, "Saving object can't be nullable.");

        updateSaveTime(item);
        getItemRepository().save(item);
    }

    @Override
    public T getById(final long id) {
        return getItemRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("Model isn't found. Required model id = " + id));
    }

    @Override
    @Transactional
    public void delete(final T item) {
        getItemRepository().delete(item);
    }

    protected abstract JpaRepository<T, Long> getItemRepository();

    public String getQueryText(final String freeText) {
        return Optional.ofNullable(freeText).map(String::trim).orElse(StringUtils.EMPTY);
    }

    public Pageable buildPageRequest(final SearchQuery searchQuery) {
        final Sort sort = Sort.by(searchQuery.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, searchQuery.getSortField());
        return PageRequest.of(searchQuery.getPage(), searchQuery.getLimit(), sort);
    }
}
