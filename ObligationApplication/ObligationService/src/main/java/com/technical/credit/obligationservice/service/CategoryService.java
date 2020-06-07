package com.technical.credit.obligationservice.service;


import com.technical.credit.obligationservice.model.CategoryModel;
import com.technical.credit.obligationservice.model.UserModel;

import java.util.List;
import java.util.Set;

public interface CategoryService extends ModelService<CategoryModel> {

    List<CategoryModel> search(SearchQuery searchQuery, UserModel user);

    Set<CategoryModel> findByParentCategoryId(Long parentCategoryId);
}
