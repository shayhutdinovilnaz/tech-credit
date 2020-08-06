package com.technical.credit.obligationservice.service;


import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.ObligationModel;
import com.technical.credit.obligationservice.model.UserModel;

import java.util.List;

public interface ObligationService extends ModelService<ObligationModel> {
    /**
     * The retrieving obligations in the system.
     *
     * @param searchQuery the search query instance
     * @param user        the user model
     * @return list of search result obligations
     */
    List<ObligationModel> search(SearchQuery searchQuery, UserModel user);

    /**
     * Method add obligation to the category.
     *
     * @param obligation - the obligation
     * @param category - the category
     */
    void addToCategory(ObligationModel obligation, CategoryModel category);
}
