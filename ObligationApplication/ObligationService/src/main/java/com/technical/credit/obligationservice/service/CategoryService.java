package com.technical.credit.obligationservice.service;

import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.UserModel;

import java.util.List;

public interface CategoryService extends ModelService<CategoryModel> {

    /**
     * The retrieving categories for user.
     *
     * @param owner the user model
     * @return list of search result categories
     */
    List<CategoryModel> getCategoriesByOwner(UserModel owner);

    /**
     * The retrieving root categories for user.
     *
     * @param owner the user model
     * @return list of search result root categories
     */

    List<CategoryModel> getRootCategoriesByOwner(UserModel owner);
}
