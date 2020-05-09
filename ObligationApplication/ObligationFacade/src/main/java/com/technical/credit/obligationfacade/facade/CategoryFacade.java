package com.technical.credit.obligationfacade.facade;

import com.technical.credit.obligationfacade.data.CategoryData;
import com.technical.credit.obligationservice.model.CategoryModel;

import java.util.List;
import java.util.Set;

public interface CategoryFacade {

    CategoryData save(CategoryData categoryData);

    CategoryData search(long id);

    List<CategoryData> search(String freeText, String sortField, boolean desc, int page, int limit);

    Set<CategoryModel> findByParentCategoryId(Long parentCategoryId);

    void delete(long id);
}
