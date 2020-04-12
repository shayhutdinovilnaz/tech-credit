package com.technical.credit.core.service.impl;

import com.technical.credit.core.exception.ModelNotFoundException;
import com.technical.credit.core.model.ItemModel;
import com.technical.credit.core.service.ModelService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

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
}
