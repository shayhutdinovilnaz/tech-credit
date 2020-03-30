package com.technical.credit.core.service;

import com.technical.credit.core.model.ItemModel;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 30.03.2020
 */
public interface ModelService<T extends ItemModel> {
    /**
     * Method saves item in the inner repository.
     *
     * @param item The saving item.
     */
    void save(T item);

    /**
     * Method delete item in the inner repository.
     *
     * @param item The deleting item.
     */
    void delete(T item);

    /**
     * Method return item by ID.
     *
     * @param id Id of item.
     * @return The result of method.
     */
    T getById(Long id);
}
