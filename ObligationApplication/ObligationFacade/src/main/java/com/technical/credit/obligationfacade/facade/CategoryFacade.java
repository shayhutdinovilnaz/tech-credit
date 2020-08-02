package com.technical.credit.obligationfacade.facade;

import com.technical.credit.obligationfacade.data.CategoryData;

import java.util.List;

public interface CategoryFacade {
    /**
     * The saving category in the system.
     * If category with a same id has been already saved - then to do update the exist instance.
     * Localized fields are saved and populated according to request localization.
     *
     * @param category instance of category for saving
     * @return the saved category
     */
    CategoryData save(CategoryData category);

    /**
     * The searching of category by id.
     * Localized fields are saved and populated according to request localization.
     *
     * @param id id of require category
     * @return the found category.
     */
    CategoryData search(long id);


    /**
     * The searching of root-categories for current user.
     * Localized fields are saved and populated according to request localization.
     *
     * @return the found category.
     */
    List<CategoryData> searchRootCategories();

    /**
     * Delete of category by id.
     *
     * @param id - the id of delete category
     */
    void delete(long id);

}
