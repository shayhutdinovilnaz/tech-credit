package com.technical.credit.core.service;

import com.technical.credit.core.model.ItemModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 30.03.2020
 */
public interface ModelService<T extends ItemModel> {
    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param item must not be {@literal null}.
     * @return the saved entity; will never be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     */
    void save(T item);

    /**
     * Deletes a given item.
     *
     * @param item The deleting item.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T item);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    T getById(long id);

    default void updateSaveTime(final T item) {

        final Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        if (item.getCreatedTime() == null) {
            item.setCreatedTime(currentDate);
        }

        item.setModifiedTime(currentDate);
    }
}
